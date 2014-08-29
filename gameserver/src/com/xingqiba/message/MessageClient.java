package com.xingqiba.message;

import org.jboss.netty.channel.Channel;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageClient {
	public String playerName = "";
	public String playerId = "";
	public String playerGroupId = "0";
	public Channel playerSocketChannel;
	
	/**
	 * 
	 * @param playerSocketChannel
	 * @param playerId
	 * @param playerGroupId
	 * @param playerName
	 */
	public MessageClient(Channel playerSocketChannel, String playerId, String playerGroupId, String playerName) {
		this.playerSocketChannel = playerSocketChannel;
		this.playerId = playerId;
		this.playerGroupId = playerGroupId;
		this.playerName = playerName;
	}
	
}
