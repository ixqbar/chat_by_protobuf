package com.xingqiba.notice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.xingqiba.util.Config;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class Notice {	
	private static final Log log = LogFactory.getLog(Notice.class);
	private static Notice instance;
	private JedisPool redisPool;
	private Jedis jedis;
	private String noticeQueueKey;
	
	/**
	 * 
	 */
	private Notice () {
		this.noticeQueueKey = Config.getSetting("noticeQueueKey");
		this.redisPool = new JedisPool(Config.getSetting("redisCacheServerIp"), Integer.parseInt(Config.getSetting("redisCacheServerPort")));
		this.jedis = this.redisPool.getResource();
		this.jedis.select(Integer.parseInt(Config.getSetting("redisCacheServerDatabase")));
	}

	/**
	 * 
	 * @return
	 */
	public static Notice getInstance() {
		if (null == instance) {
			instance = new Notice();
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNotice() {
		log.info("get notice dequeue");
		
		String notice = "";
		long noticeLength = this.jedis.llen(this.noticeQueueKey);
		if (noticeLength > 0) {
			notice = this.jedis.lpop(this.noticeQueueKey);
		}
		
		return notice;
	}
}
