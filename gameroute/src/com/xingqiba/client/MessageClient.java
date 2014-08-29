package com.xingqiba.client;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.xingqiba.client.MessageClientPipelineFactory;
import com.xingqiba.notice.Notice;
import com.xingqiba.server.GameSocketServer;
import com.xingqiba.util.MessageProtoHandler.ChatMessage;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageClient {
	private static final Log log = LogFactory.getLog(MessageClient.class);
	public static Map<Channel, GameSocketServer> messageClientMap = new ConcurrentHashMap<Channel, GameSocketServer>();
	private ClientBootstrap bootstrap;
	private Notice noticeHandle;
	
	/**
	 * 
	 */
	public MessageClient() {
		this.bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));        
        this.bootstrap.setPipelineFactory(new MessageClientPipelineFactory());
        this.noticeHandle = Notice.getInstance();
	}
	
	/**
	 * 
	 */
	public void close() {
		this.bootstrap.releaseExternalResources();
	}
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @param status
	 */
	public void start(String ip, int port, int status) {
		log.info(ip + ":" + port + ":" + status);
		switch (status) {
			case 1:
				this.connect(ip, port);
				break;
			case 0:
				this.disconnect(ip, port);
				break;
		}
	}
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @return
	 */
	public void connect(String ip, int port) {
		log.info("connect " + ip + ":" + port);
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(ip, port));
		GameSocketServer gameSocketServerHandle = new GameSocketServer(ip, port);
		gameSocketServerHandle.channel = future.getChannel();		
		MessageClient.messageClientMap.put(future.getChannel(), gameSocketServerHandle);
	}
	
	/**
	 * 
	 * @param ip
	 * @param port
	 */
	public void disconnect(String ip, int port) {
		for (Map.Entry<Channel, GameSocketServer>entry : MessageClient.messageClientMap.entrySet()) {
			if (entry.getValue().ip.equals(ip) && entry.getValue().port == port) {
				entry.getValue().close();
				MessageClient.messageClientMap.remove(entry.getKey());
				break;
			} 
		}
	}
	
	/**
	 * 
	 * @param receivedMessageChannel
	 * @param message
	 * @return
	 */
	public static boolean dispatch(ChannelFuture receivedMessageFuture, byte[] message) {
		if (MessageClient.messageClientMap.size() <= 0) {
			return false;
		}	
		
		log.info("message dispatch");
		for (Map.Entry<Channel, GameSocketServer>entry : MessageClient.messageClientMap.entrySet()) {
			if (entry.getKey() != receivedMessageFuture && 1 == entry.getValue().status) {
				entry.getValue().write(message);			
			} 
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param connectedChannel
	 * @return
	 */
	public static boolean connected(Channel connectedChannel) {
        GameSocketServer gameSocketServerHandle = MessageClient.messageClientMap.get(connectedChannel);
		gameSocketServerHandle.init();
		MessageClient.messageClientMap.put(connectedChannel, gameSocketServerHandle);
		return true;
	}
	
	/**
	 * 
	 * @param closedChannel
	 * @return
	 */
	public static boolean closed(Channel closedChannel) {
		GameSocketServer gameSocketServerHandle = MessageClient.messageClientMap.get(closedChannel);
		gameSocketServerHandle.close();
		MessageClient.messageClientMap.put(closedChannel, gameSocketServerHandle);
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean check() {
		for (Map.Entry<Channel, GameSocketServer>entry : MessageClient.messageClientMap.entrySet()) {
			//判断运行状态
			if (2 == entry.getValue().status) {
				this.connect(entry.getValue().ip, entry.getValue().port);
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean postNotice() {
		String notice = this.noticeHandle.getNotice();
		if (0 == notice.length()) {
			return false;
		}
		
		boolean postFlag = false;
		for (Map.Entry<Channel, GameSocketServer>entry : MessageClient.messageClientMap.entrySet()) {
			//判断运行状态
			if (1 == entry.getValue().status) {
				postFlag = true;
				break;
			}
		}
		
		if (false == postFlag) {
			return false;
		}
		
		ChatMessage chatWorldMessage = ChatMessage.newBuilder()
				.setChatChannel(200)
				.setMessage(notice)
				.build();
		byte[] noticeByte = chatWorldMessage.toByteArray();
		
		for (Map.Entry<Channel, GameSocketServer>entry : MessageClient.messageClientMap.entrySet()) {
			//判断运行状态
			if (1 == entry.getValue().status) {
				entry.getValue().write(noticeByte);
			}
		}
		
		return true;
	}
}
