<?php

class NoticeQueue {
    private $_socket_handle = null;
    private $_configs = array();
    private $_chatMessage_handle = null;

    private function __construct() {
        $this->_chatMessage_handle = new ChatMessage();
        $this->_configs = parse_ini_file(ROOT . '/config/app.ini');
        $this->runSocket();
    }

    private function runSocket() {
        $this->_socket_handle = @socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
        @socket_connect($this->_socket_handle, $this->_configs['socketAddress'], $this->_configs['socketPort']);
    }

    public static function instance() {
        return new self();
    }
    
    /**
     * 
     */
    public function deQueue() {
        /**
         * ....读取队列消息(省略).....
         * 
         */
        
        //拼装消息
        $this->_chatMessage_handle->set_chatChannel(200);
        $this->_chatMessage_handle->set_message("Hello,This is a test!");
        $message = $this->_chatMessage_handle->SerializeToString();
        
        //打包
        $pack = pack("NA" . strlen($message), strlen($message), $message);
        
        //发送
        $length = strlen($pack);
        $offset = 0;
        while ($offset < $length) {
            $sent = socket_write($this->_socket_handle, substr($pack, $offset), $length-$offset);
            if ($sent === false) {
                break;
            }
            $offset += $sent;
        }
        if ($offset < $length) {
            $errorcode = socket_last_error();
            $errormsg = socket_strerror($errorcode);
            echo "SENDING ERROR: $errormsg\n";
        } 
        
        /**
         * ....其他处理(省略).....
         * 
         */
    }
}