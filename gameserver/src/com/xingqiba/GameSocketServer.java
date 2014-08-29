package com.xingqiba;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.xingqiba.util.Config;
import com.xingqiba.server.MessageServerPipelineFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.channel.ChannelFactory;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class GameSocketServer  {
	
	private static final Log log = LogFactory.getLog(GameSocketServer.class);
	
	static {
		PropertyConfigurator.configure("config/log4j.properties");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("config port = " + Config.getSetting("port"));
		
		ChannelFactory factory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
		
		ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setPipelineFactory(new MessageServerPipelineFactory());
        bootstrap.setOption("tcpNoDelay", true);  
        bootstrap.setOption("keepAlive", true);  
        bootstrap.setOption("reuseAddress", true);
        
        bootstrap.bind(new InetSocketAddress(Integer.parseInt(Config.getSetting("port"))));
				
		log.info("server running");
	}
	
}
