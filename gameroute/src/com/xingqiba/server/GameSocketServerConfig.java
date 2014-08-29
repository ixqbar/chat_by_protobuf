package com.xingqiba.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xingqiba.exception.ConfigException;

/**
 * @TODO
 * @author ixqbar@gmail.com
 *
 */
public class GameSocketServerConfig {
	private final static Log log = LogFactory.getLog(GameSocketServerConfig.class);		
	private Map<String, GameSocketServerInfo> tempGameServerConfigMap = new ConcurrentHashMap<String, GameSocketServerInfo>();
	private Map<String, GameSocketServerInfo> notifiedGameSocketServerMap = new ConcurrentHashMap<String, GameSocketServerInfo>();
	private Map<String, GameSocketServerInfo> defaultGameSocketServerMap = new ConcurrentHashMap<String, GameSocketServerInfo>();
	private String configPath = "config/server.properties";
	
	/**
	 * 
	 */
	public void reload() {
		try {
			this.readConfigFile();
		} catch (ConfigException e) {
			throw new ConfigException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 */
	private synchronized void readConfigFile() {
		File file = new File(this.configPath);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			Properties p = new Properties();
			p.load(in);
			Enumeration<?> item = p.propertyNames();
			while (item.hasMoreElements()) {
				String key = (String) item.nextElement();
				String[] server = p.getProperty(key).split(",");
				if (server.length == 2) {
					this.defaultGameSocketServerMap.put(key, new GameSocketServerInfo(server[0], Integer.parseInt(server[1]), 1));
				} else {
					throw new ConfigException("error server config at " + key);
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			log.error("config file not found at" + file.getAbsolutePath());
			throw new ConfigException("FileNotFoundException", e);
		} catch (IOException e) {
			log.error("config file not found at" + file.getAbsolutePath());
			throw new ConfigException("IOException", e);
		} catch (Exception e) {
			throw new ConfigException("Exception", e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, GameSocketServerInfo> checkServerConfigNotify() {
		if (this.notifiedGameSocketServerMap.size() > 0) {
			this.notifiedGameSocketServerMap.clear();
		}
		
		//重新加载
		this.defaultGameSocketServerMap.clear();
		this.reload();
		
		//检测添加
		if (this.defaultGameSocketServerMap.size() > 0) {
			for (Map.Entry<String, GameSocketServerInfo>entry : this.defaultGameSocketServerMap.entrySet()) {
				if (this.tempGameServerConfigMap.containsKey(entry.getKey())) {
					continue;
				}
				
				this.notifiedGameSocketServerMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		//检测删除
		if (this.tempGameServerConfigMap.size() > 0) {
			for (Map.Entry<String, GameSocketServerInfo>entry : this.tempGameServerConfigMap.entrySet()) {
				 if (this.defaultGameSocketServerMap.containsKey(entry.getKey())) {
					 continue;
				 }
				 //修改其状态
				 entry.getValue().status = 0;
				 this.notifiedGameSocketServerMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		//清理
		this.tempGameServerConfigMap.clear();		
		if (this.defaultGameSocketServerMap.size() > 0) {			
			for (Map.Entry<String, GameSocketServerInfo>entry : this.defaultGameSocketServerMap.entrySet()) {
				this.tempGameServerConfigMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		return this.notifiedGameSocketServerMap;
	}
	
}
