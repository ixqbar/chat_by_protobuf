package com.xingqiba.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

import com.xingqiba.util.Config;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageServerPipelineFactory implements ChannelPipelineFactory {
	
	private static final Log log = LogFactory.getLog(MessageServerPipelineFactory.class);
	
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		
		ChannelPipeline pipeline = Channels.pipeline();
		
		if (1 == Integer.parseInt(Config.getSetting("idleCheck"))) {
			log.info("open idle check");
			Timer timer = new HashedWheelTimer();
			int clientReaderIdleTimeSeconds = Integer.parseInt(Config.getSetting("clientReaderIdleTimeSeconds"));
			int clientWriterIdleTimeSeconds = Integer.parseInt(Config.getSetting("clientWriterIdleTimeSeconds"));
			
			pipeline.addLast("timeout", new IdleStateHandler(timer, clientReaderIdleTimeSeconds, clientWriterIdleTimeSeconds, 0));
			pipeline.addLast("idle", new MessageIdle());
		}
		
        pipeline.addLast("decoder", new MessageDecoder());
        pipeline.addLast("encoder", new MessageEncoder());
        pipeline.addLast("handler", new MessageServerHandler());
 
        return pipeline;
	}

}
