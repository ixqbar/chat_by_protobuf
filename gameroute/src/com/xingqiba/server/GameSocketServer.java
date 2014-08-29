package com.xingqiba.server;

import org.jboss.netty.channel.Channel;

import com.xingqiba.util.MessageProtoHandler.ChatMessage;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class GameSocketServer {
	public String ip;
	public int port;
	public int status = 0;
	public Channel channel;
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @param status
	 */
	public GameSocketServer(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public boolean write(byte[] message) {
		if (message.length <= 0) {
			return false;
		}
		
		this.channel.write(message);
		
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean init() {
        ChatMessage chatInitMessage = ChatMessage.newBuilder()
				.setChatChannel(500)
				.build();
        this.channel.write(chatInitMessage.toByteArray());
        this.status = 1;
        
		return true;
	}
	
	/**
	 * 
	 */
	public void close() {
		this.channel.close();
		this.channel.getCloseFuture().awaitUninterruptibly();
		this.status = 2;
	}
}
