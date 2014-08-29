package com.xingqiba.message;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xingqiba.util.ConcurrentHashSet;
import com.xingqiba.util.Config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessagePlayerInfo {
	private static final Log log = LogFactory.getLog(MessagePlayerInfo.class);
	private static MessagePlayerInfo instance;
	private Map<String, String> playerNameMap = new ConcurrentHashMap<String, String>();
	private Map<String, String> playerGroupIdMap = new ConcurrentHashMap<String, String>();
	private JedisPool redisPool;
	private Jedis jedis;
	private Set<String> ignoreDirtyWordsChars = new ConcurrentHashSet<String>();
	private String ignoreDirtyWordsCacheKey = "ignoreDirtyWords";
	private String dirtyWordsCacheKey = "dirtyWords";
	private int ignoreDirtyWordsStep = 20;
	
	/**
	 * 
	 */
	private MessagePlayerInfo () {
		this.redisPool = new JedisPool(Config.getSetting("redisCacheServerIp"), Integer.parseInt(Config.getSetting("redisCacheServerPort")));
		this.jedis = this.redisPool.getResource();
		this.jedis.select(Integer.parseInt(Config.getSetting("redisCacheServerDatabase")));
		this.initIgnoreDirtyWords();
	}
	
	/**
	 * 
	 */
	private void initIgnoreDirtyWords () {
		if (0 == Integer.parseInt(Config.getSetting("dirtyWordsFilter"))) {
			return;
		}
		
		this.ignoreDirtyWordsStep = Integer.parseInt(Config.getSetting("ignoreDirtyWordsStep"));		
		if (this.jedis.exists(this.ignoreDirtyWordsCacheKey)) {
			this.ignoreDirtyWordsChars = this.jedis.smembers(this.ignoreDirtyWordsCacheKey);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static MessagePlayerInfo getInstance() {
		if (null == instance) {
			instance = new MessagePlayerInfo();
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @param playerId
	 * @return
	 */
	public String getPlayerName(String playerId) {
		String playerName = "NOT FOUND";
		
		if (this.playerNameMap.containsKey(playerId)) {
			playerName = this.playerNameMap.get(playerId);
		} else {
			try {
				List<String> playerNames = this.jedis.hmget("player:" + playerId, "playerName");
				if (1 == playerNames.size()) {
					playerName = playerNames.get(0);
					this.playerNameMap.put(playerId, playerName);
				} else {
					log.warn(String.format("hmget %s %s failure", "player:" + playerId, "playerName"));
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return playerName;
	}
	
	/**
	 * 
	 * @param playerId
	 * @return
	 */
	public String getGroupId(String playerId) {
		String groupId = "0";
		
		if (this.playerGroupIdMap.containsKey(playerId)) {
			groupId = this.playerGroupIdMap.get(playerId);
		} else {
			try {
				List<String> playerGroups = this.jedis.hmget("player:" + playerId, "playerGroup");
				if (1 == playerGroups.size()) {
					groupId = playerGroups.get(0);
					this.playerGroupIdMap.put(playerId, groupId);
				} else {
					log.warn(String.format("hmget %s %s failure", "player:" + playerId, "playerGroup"));
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
		return groupId;
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	public String dirtyWordsFilter(String beforeFilterMessage) {
		String afterFilterMessage = new String(beforeFilterMessage);
		int wordsLength = beforeFilterMessage.length();
		log.info("before filter:" + beforeFilterMessage);
		for (int step = 0; step < wordsLength; step++) {
			String stepWord = String.valueOf(beforeFilterMessage.charAt(step));
			
			if (0 < this.ignoreDirtyWordsChars.size() && this.ignoreDirtyWordsChars.contains(stepWord)) {
				continue;
			}
			
			Long index = this.jedis.zrank(this.dirtyWordsCacheKey, stepWord);
			if (index == null || index.intValue() <= 0) {
				continue;
			}
			
			Set<String> ignoreWordSet = this.jedis.zrange(this.dirtyWordsCacheKey, index.intValue(), index.intValue() + this.ignoreDirtyWordsStep);
			for (String ignoreWord : ignoreWordSet) {
				if (ignoreWord.endsWith("*")) {
					afterFilterMessage = afterFilterMessage.replace(ignoreWord.substring(0, ignoreWord.length()-1), "*");
				}
			}
		}
		log.info("after filter:" + afterFilterMessage);
		
		
		return afterFilterMessage;
	}
}
