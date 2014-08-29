/**
 *
 * @author ixqbar@gmail.com
 */
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <signal.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/param.h>
#include <errno.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/epoll.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdarg.h>

#define REQUEST_POLICY "<policy-file-request/>"
#define RESPONSE_POLICY "<?xml version=\"1.0\"?><cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0"
#define DEBUG_PRINT 0
#define DEBUG_LOG 0
#define DEBUG_ERROR 0
#define DEBUG_WARN 0
#define MAX_CLIENT 10000

//函数声明
//守护进程
static void runAsDaemon(void);
//信号处理
static void kill_signal(const int);
//服务器
static void runChatServer(const char *);
//非阻塞
static void setnonblocking(int);
static void debug_print(int, const char *,...);
//用户验证

//主函数
int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("You must useage like %s 127.0.0.1 0 or %s 127.0.0.1 1\n", argv[0], argv[0]);
        exit(EXIT_FAILURE);
    }
    //信号处理
    signal(SIGPIPE, SIG_IGN);
    signal(SIGINT, kill_signal);
    signal(SIGKILL, kill_signal);
    signal(SIGQUIT, kill_signal);
    signal(SIGTERM, kill_signal);
    signal(SIGHUP, kill_signal);
    //守护进程
    if (atoi(argv[2]) == 1) {
        runAsDaemon();
    }
    //服务器
    runChatServer(argv[1]);
    //返回
    exit(EXIT_SUCCESS);
}

/**
 * 日志记录
 * @param flag
 * @param fmt
 * @param ...
 */
static void debug_print(int flag, const char *fmt, ...) {
    char *info;
    int size = 1024;
    int n;
    info = (char *)malloc(size);

    while(1) {
        va_list ap;
        va_start(ap, fmt);
        n = vsnprintf(info, size, fmt, ap);
        va_end(ap);
        if (n > -1 && n <size) {
            break;
        }
        size *= 2;
        info = (char *)realloc(info, size);
    }

    printf("%s",info);
    
    free(info);
}

/**
 * 守护进程
 */
static void runAsDaemon() {
    pid_t pid = fork();
    if (pid < 0) {
       exit(EXIT_FAILURE);
    }
    //parent
    if (pid > 0) {
       exit(EXIT_SUCCESS);
    }

    setsid();
    int i;
    for(i = 0; i < NOFILE; ++i) {
        close(i);
    }
    umask(0);
}

static void kill_signal(const int sig) {
    //退出
    exit(EXIT_SUCCESS);
}

/**
 * 非阻塞
 * @param sock
 */
static void setnonblocking(int sock) {
    int opts;
    opts = fcntl(sock,F_GETFL);
    if(opts<0) {
        perror("fcntl(sock,GETFL)");
        exit(1);
    }
    opts = opts|O_NONBLOCK;
    if(fcntl(sock,F_SETFL,opts)<0) {
        perror("fcntl(sock,SETFL,opts)");
        exit(1);
    }
}

/**
 * 服务器
 */
static void runChatServer(const char *ip) {
    int sockfd;
    struct sockaddr_in server, client;
    socklen_t client_len = sizeof(struct sockaddr_in);

    memset(&server, 0, sizeof(struct sockaddr_in));

    server.sin_family = AF_INET;
    server.sin_port = htons(843);
    server.sin_addr.s_addr = inet_addr(ip);
    
    //socket
    if (-1 == (sockfd = socket(AF_INET, SOCK_STREAM, 0))) {
        debug_print(DEBUG_ERROR, "%s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    //reuseaddr
    int flag=1,len=sizeof(int);
    if (-1 == setsockopt(sockfd, SOL_SOCKET, SO_REUSEADDR, &flag, len)) {
        debug_print(DEBUG_ERROR, "%s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    //bind
    if (-1 == bind(sockfd, (struct sockaddr *)&server, sizeof(server))) {
        debug_print(DEBUG_ERROR, "%s\n", strerror(errno));
        exit(EXIT_FAILURE);
    }
    listen(sockfd, 10);

    //epoll
    int epollfd = epoll_create(MAX_CLIENT);

    if (-1 == epollfd) {
        debug_print(DEBUG_ERROR, "%s\n", strerror(errno));
        exit(EXIT_SUCCESS);
    }

    struct epoll_event evfd,events[MAX_CLIENT];
    evfd.events = EPOLLIN || EPOLLET;
    evfd.data.fd = sockfd;

    if (-1 == epoll_ctl(epollfd, EPOLL_CTL_ADD, sockfd, &evfd)) {
        debug_print(DEBUG_ERROR, "%s\n", strerror(errno));
        exit(EXIT_SUCCESS);
    }

    int evnum, n, conn_sock;
    //启动
    while (1) {
        debug_print(DEBUG_LOG, "waiting...\n");
        evnum = epoll_wait(epollfd, events, MAX_CLIENT, -1);
        if (evnum > 0) {
			debug_print(DEBUG_LOG, "client connected\n");
            for(n = 0; n < evnum; ++n) {
                if (events[n].data.fd == sockfd) {
                    conn_sock = accept(sockfd, (struct sockaddr *)&client, &client_len);
                    if (conn_sock == -1) {
                        debug_print(DEBUG_LOG, "accept failure\n");
                        exit(EXIT_FAILURE);
                    }
                    setnonblocking(conn_sock);
                    //事件注册
                    evfd.events = EPOLLIN | EPOLLET;
                    evfd.data.fd = conn_sock;
                    if (epoll_ctl(epollfd, EPOLL_CTL_ADD, conn_sock, &evfd) == -1) {
                        exit(EXIT_FAILURE);
                    }
                }
                else {
                    char message[BUFSIZ];
                    int message_len = recv(events[n].data.fd, message, BUFSIZ - 1, 0);
                    debug_print(DEBUG_LOG, "receive message %s\n", message);
                    if (message_len > 0 && 0 == strcasecmp(message, REQUEST_POLICY)) {
                        send(events[n].data.fd, RESPONSE_POLICY, strlen(RESPONSE_POLICY), 0);
                    }
                    close(events[n].data.fd);
                    epoll_ctl(epollfd, EPOLL_CTL_DEL, events[n].data.fd, &evfd);
                }
            }
        }
    }
    //关闭文件
    close(sockfd);
    close(epollfd);
}
