package com.xingqiba.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import com.xingqiba.message.MessageMapRelation;
import com.xingqiba.util.Config;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageIdle extends IdleStateAwareChannelHandler {
	private static final Log log = LogFactory.getLog(MessageIdle.class);
	int clientIdleCheckedMax = Integer.parseInt(Config.getSetting("clientIdleCheckedMax"));
	int clientIdleChecked = 0;
		
	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
		super.channelIdle(ctx, e);
		
		if (e.getState() == IdleState.WRITER_IDLE) {
			clientIdleChecked++;
		}
		
		if (clientIdleChecked <= clientIdleCheckedMax) {
			return;
		}
		
		if (MessageMapRelation.routeSocketChannelSet.contains(e.getChannel())) {
			return;
		}	
		
		log.info("client timeout " + e.getChannel().getRemoteAddress());
		e.getChannel().close();			
	}
 
}
