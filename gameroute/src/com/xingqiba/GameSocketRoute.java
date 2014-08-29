package com.xingqiba;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.xingqiba.client.MessageClient;
import com.xingqiba.server.GameSocketServerConfig;
import com.xingqiba.server.GameSocketServerInfo;
import com.xingqiba.util.Config;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class GameSocketRoute {
	private static final Log log = LogFactory.getLog(GameSocketRoute.class);
	
	static {
		PropertyConfigurator.configure("config/log4j.properties");
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		log.info("route start running");
		
		long interval = 1000 * Integer.parseInt(Config.getSetting("checkInterval")); 
		MessageClient messageClientHandle = new MessageClient();
		GameSocketServerConfig serverConfigHandle = new GameSocketServerConfig();
		
		while(true) {
			Map<String, GameSocketServerInfo> gameSocketServerMap = serverConfigHandle.checkServerConfigNotify();
			if (gameSocketServerMap.size() > 0) {
				for (Map.Entry<String, GameSocketServerInfo>entry : gameSocketServerMap.entrySet()) {					
					messageClientHandle.start(entry.getValue().ip, entry.getValue().port, entry.getValue().status);							
				}
			}
			
			if (0 == MessageClient.messageClientMap.size()) {
				log.info("empty client map");
				break;
			}
			
			messageClientHandle.check();
			messageClientHandle.postNotice();
			
			log.info("sleep");			
			Thread.sleep(interval);
		}
				
		log.info("route stop");
		messageClientHandle.close();
	}

}
