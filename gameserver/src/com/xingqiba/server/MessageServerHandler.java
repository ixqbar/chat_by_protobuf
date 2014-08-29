package com.xingqiba.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.xingqiba.exception.MessageException;
import com.xingqiba.message.MessageDispatchHandler;
import com.xingqiba.message.MessageMapRelation;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageServerHandler extends SimpleChannelUpstreamHandler  {
	private static final Log log = LogFactory.getLog(MessageServerHandler.class);
 
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) 
    		throws Exception {
        try {
        	MessageDispatchHandler messageDispatchHandler = new MessageDispatchHandler((byte[])e.getMessage(),e);
        	messageDispatchHandler.dispatch();        	
        } catch (MessageException mException) {
			log.error(mException.getMessage());
		} catch (Exception fException) {
			log.error(fException.getMessage());
		}
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) 
    		throws Exception {
        log.info("client exceptionCaught " + e.getChannel().getRemoteAddress());
        e.getChannel().close();
    }

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.info("client disconnected " + e.getChannel().getRemoteAddress());
		//清理映射
		MessageMapRelation.clearAll(e.getChannel());
		e.getChannel().close();		
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.info("client connected " + e.getChannel().getRemoteAddress());
	}
        
}
