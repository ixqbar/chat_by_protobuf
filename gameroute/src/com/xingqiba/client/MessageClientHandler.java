package com.xingqiba.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xingqiba.util.MessageProtoHandler.ChatMessage;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageClientHandler extends SimpleChannelUpstreamHandler {
	
	private static final Log log = LogFactory.getLog(MessageClientHandler.class);
	
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    	try {
    		ChatMessage chatMessageHandle = ChatMessage.parseFrom((byte[])e.getMessage());
			//log
			log.info(String.format("receive|channle=%d|from=%s|group=%s|name=%s|to=%s|message=%s", 
					chatMessageHandle.getChatChannel(),
					chatMessageHandle.getFromId(),
					chatMessageHandle.getGroupId(),
					chatMessageHandle.getName(),
					chatMessageHandle.getToId(),
					chatMessageHandle.getMessage()));			
			//分发
    		MessageClient.dispatch(e.getFuture(), (byte[])e.getMessage());
    		
		} catch (InvalidProtocolBufferException protoException) {
			log.error(protoException.getMessage());
		}
    }
 
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
    	log.info(e.getCause());
        e.getChannel().close();
    }
    
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.info("connection closed");
		super.channelClosed(ctx, e);
		MessageClient.closed(e.getChannel());
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {		
		log.info("connected");
		super.channelConnected(ctx, e);		
		MessageClient.connected(e.getChannel());		
	}
    
}
