package com.xingqiba.message;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;

import com.xingqiba.util.ConcurrentHashSet;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageMapRelation {	
	private static final Log log = LogFactory.getLog(MessageMapRelation.class);
	
	public static Map<Channel, String> playerSocketChannelMap = new ConcurrentHashMap<Channel, String>();
	public static Map<String, MessageClient> playerMap = new ConcurrentHashMap<String, MessageClient>();
	public static Set<Channel> routeSocketChannelSet = new ConcurrentHashSet<Channel>();
	public static Set<MessageClient> groupPlayerSet = new ConcurrentHashSet<MessageClient>();
	public static Map<String,Set<MessageClient>> groupMap = new ConcurrentHashMap<String,Set<MessageClient>>();
	
	/**
	 * 
	 * @param groupId
	 * @param messagePlayer
	 * @return
	 */
	public static void addGroupPlayer(String groupId, MessageClient messagePlayer) {
		groupPlayerSet.add(messagePlayer);
		groupMap.put(groupId, groupPlayerSet);
	}
	
	/**
	 * 
	 * @param groupId
	 * @param messagePlayer
	 */
	public static void removeGroupPlayer(String groupId, MessageClient messagePlayer) {
		groupPlayerSet.remove(messagePlayer);
		groupMap.put(groupId, groupPlayerSet);
	}
	
	/**
	 * 
	 * @param playerId
	 */
	public static void clearAll(Channel clientSocketChannel) {
		if (MessageMapRelation.routeSocketChannelSet.size() > 0 && MessageMapRelation.routeSocketChannelSet.contains(clientSocketChannel)) {
			MessageMapRelation.routeSocketChannelSet.remove(clientSocketChannel);
			log.info("clear route mapping " + clientSocketChannel.getRemoteAddress());
		} else if (MessageMapRelation.playerSocketChannelMap.size() > 0 && null != MessageMapRelation.playerSocketChannelMap.get(clientSocketChannel)) {
			String playerId = MessageMapRelation.playerSocketChannelMap.get(clientSocketChannel);
			MessageClient messagePlayer = MessageMapRelation.playerMap.get(playerId);
			MessageMapRelation.playerSocketChannelMap.remove(clientSocketChannel);
			log.info("clear socket mapping " + clientSocketChannel.getRemoteAddress());
			MessageMapRelation.playerMap.remove(playerId);
			log.info("clear player mapping " + clientSocketChannel.getRemoteAddress());
			if (messagePlayer.playerGroupId.length() > 0) {
				MessageMapRelation.removeGroupPlayer(messagePlayer.playerGroupId, messagePlayer);
				log.info("clear group mapping " + clientSocketChannel.getRemoteAddress());
			}			
		} else {
			log.info("empty map relation");
		}
	}	
}
