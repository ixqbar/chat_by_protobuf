#!/usr/bin/php
<?php
require_once 'include/common.php';
require_once 'lib/NoticeQueue.class.php';
require_once 'lib/googlebuf/message/pb_message.php';
require_once 'lib/ChatMessage.class.php';

$queue_handle = NoticeQueue::instance();
//kill signal
declare(ticks = 1);
$exit_signo = 0;

function work_exit($signo) {
    global $exit_signo;
    $exit_signo = $signo;
}

pcntl_signal(SIGTERM, "work_exit");
pcntl_signal(SIGINT, "work_exit");
pcntl_signal(SIGUSR1, "work_exit");

echo "[" . date('Y-m-d H:i:s') . "]start run\n";
while (1) {
    if ($exit_signo) {
        echo "[" . date('Y-m-d H:i:s') . "]catch kill signal=" . $exit_signo . "\n";
        break;
    }

    try {
        $queue_handle->deQueue();
    } catch (Exception $e) {
        echo "[" . date('Y-m-d H:i:s') . "]Message:" . $e->getMessage() . ' Trace' . $e->getTraceAsString() . "\n";
    }
    
    sleep(5);    
}
echo "[" . date('Y-m-d H:i:s') . "]stop run\n";
?>
