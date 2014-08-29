package com.xingqiba.message;

import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.Channel;

import com.google.protobuf.InvalidProtocolBufferException;
import com.xingqiba.exception.MessageException;
import com.xingqiba.util.MessageProtoHandler.ChatMessage;

/**
 * 
 * @author ixqbar@gmail.com
 *
 */
public class MessageDispatchHandler {
	private static final Log log = LogFactory.getLog(MessageDispatchHandler.class);
	private ChatMessage chatMessageHandle;
	private Channel playerSocketChannel;
	private byte[] chatMessageByte;
	
	/**
	 * 
	 * @param message
	 * @param e
	 * @throws InvalidProtocolBufferException 
	 */
	public MessageDispatchHandler (byte[] message, MessageEvent e) throws InvalidProtocolBufferException {
		this.chatMessageByte = message;
		this.chatMessageHandle = ChatMessage.parseFrom(message);
		this.playerSocketChannel = (Channel) e.getChannel();
	}
	
	/**
	 * 
	 */
	public void dispatch() {
		MessagePlayerInfo messagePlayerInfoHandle = MessagePlayerInfo.getInstance();
		
		int clientChatChannel = this.chatMessageHandle.getChatChannel();
		String clientFromId   = this.chatMessageHandle.getFromId();
		String clientGroupId  = this.chatMessageHandle.getGroupId();
		String clientName     = this.chatMessageHandle.getName();		
		String clientToId     = this.chatMessageHandle.getToId();
		String clientMessage  = this.chatMessageHandle.getMessage();
		
		log.info(String.format("dispatch|channle=%d|from=%s|group=%s|name=%s|to=%s|message=%s", 
				clientChatChannel,
				clientFromId,
				clientGroupId,
				clientName,
				clientToId,
				clientMessage));
		
		switch (clientChatChannel) {
			case 0:
				//心跳
				break;
				
			case 1:
				//用户公共频道
				if (clientMessage.length() > 0) {
					//过滤
					clientMessage = messagePlayerInfoHandle.dirtyWordsFilter(clientMessage);
					ChatMessage chatMessage = ChatMessage.newBuilder()
							.setChatChannel(1)
							.setFromId(clientFromId)
							.setToId(clientToId)
							.setName(clientName)
							.setGroupId(clientGroupId)
							.setMessage(clientMessage)
							.build();
					byte[] message = chatMessage.toByteArray();
					//本服务器发送
					for (Map.Entry<String, MessageClient>entry : MessageMapRelation.playerMap.entrySet()) {
						MessageClient messagePlayer = entry.getValue();
						messagePlayer.playerSocketChannel.write(message);
					}
					log.info("public send");
					//路由转发
					if (MessageMapRelation.routeSocketChannelSet.size() > 0) {
						chatMessage = ChatMessage.newBuilder()
								.setChatChannel(1000)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						message = chatMessage.toByteArray();
						for (Channel routeChannel : MessageMapRelation.routeSocketChannelSet) {
							routeChannel.write(message);
						}
						log.info("route send");
					}
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			case 2:
				//用户组频道
				if (clientMessage.length() > 0 && clientGroupId.length() > 0 && Integer.parseInt(clientGroupId) > 0) {
					//过滤
					clientMessage = messagePlayerInfoHandle.dirtyWordsFilter(clientMessage);
					ChatMessage chatMessage = ChatMessage.newBuilder()
							.setChatChannel(2)
							.setFromId(clientFromId)
							.setToId(clientToId)
							.setName(clientName)
							.setGroupId(clientGroupId)
							.setMessage(clientMessage)
							.build();
					byte[] message = chatMessage.toByteArray();
					//本服务器发送
					Set<MessageClient> messagePlayers = MessageMapRelation.groupMap.get(clientGroupId);
					for (MessageClient messagePlayer : messagePlayers) {
						messagePlayer.playerSocketChannel.write(message);
					}
					log.info("group send");
					//路由转发
					if (MessageMapRelation.routeSocketChannelSet.size() > 0) {
						chatMessage = ChatMessage.newBuilder()
								.setChatChannel(2000)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						message = chatMessage.toByteArray();
						for (Channel routeSocketChannel : MessageMapRelation.routeSocketChannelSet) {
							routeSocketChannel.write(message);
						}
						log.info("route send");
					}
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			case 3:
				//用户私聊频道
				if (clientMessage.length() > 0 && clientToId.length() > 0) {
					//过滤
					clientMessage = messagePlayerInfoHandle.dirtyWordsFilter(clientMessage);
					//本服务器发送
					if (MessageMapRelation.playerMap.containsKey(clientToId)) {
                        //目标发送
						log.info("personal target send");
						ChatMessage chatMessage = ChatMessage.newBuilder()
								.setChatChannel(3)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						byte[] message = chatMessage.toByteArray();
						MessageClient messagePlayer = MessageMapRelation.playerMap.get(clientToId);
						messagePlayer.playerSocketChannel.write(message);
					} else if (MessageMapRelation.routeSocketChannelSet.size() > 0){
						//路由转发
						log.info("personal route send");
						ChatMessage chatMessage = ChatMessage.newBuilder()
								.setChatChannel(3000)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						byte[] message = chatMessage.toByteArray();
						for (Channel routeChannel : MessageMapRelation.routeSocketChannelSet) {
							routeChannel.write(message);
						}
					} else {
						throw new MessageException("error message toId");
					}
					//自身发送
					log.info("personal self send");                   
					ChatMessage chatMessage = ChatMessage.newBuilder()
							.setChatChannel(3)
							.setFromId(clientFromId)
							.setToId(clientToId)
							.setName(messagePlayerInfoHandle.getPlayerName(clientToId))
							.setMessage(clientMessage)
							.build();
					byte[] message = chatMessage.toByteArray();
                    this.playerSocketChannel.write(message);					
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			case 100:
				//用户初始信息
				if (clientFromId.length() > 0 && clientName.length() > 0) {
					log.info("new client init");
					MessageClient messagePlayer = MessageMapRelation.playerMap.get(clientFromId);
					if (null != messagePlayer) {
						log.info("clear same player connection");
						messagePlayer.playerSocketChannel.close();
					}					
					//填充客户集合
					MessageMapRelation.playerSocketChannelMap.put(this.playerSocketChannel, clientFromId);
					//用户信息
					messagePlayer = new MessageClient(
							this.playerSocketChannel,
							clientFromId,
							clientGroupId,
							clientName
							);
					//填充用户基础信息 
					MessageMapRelation.playerMap.put(clientFromId, messagePlayer);
					//填充用户组信息
					if (clientGroupId.length() > 0 && Integer.parseInt(clientGroupId) > 0) {
						MessageMapRelation.addGroupPlayer(clientGroupId, messagePlayer);
					}
					log.info("new client init end");
				} else {
					throw new MessageException("error client fromId & name");
				}
				break;
				
			case 200:
				//系统公告
				if (clientMessage.length() > 0) {
					for (Map.Entry<String, MessageClient>entry : MessageMapRelation.playerMap.entrySet()) {
						MessageClient messagePlayer = entry.getValue();
						messagePlayer.playerSocketChannel.write(this.chatMessageByte);
					}
					log.info("notice send");					
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			case 500:
				//路由连接
				//填充路由集合
				log.info("route connect" + this.playerSocketChannel.getRemoteAddress());
				MessageMapRelation.routeSocketChannelSet.add(this.playerSocketChannel);
				break;
				
			case 1000:
				//系统转发公共频道
				if (clientMessage.length() > 0) {
					if (MessageMapRelation.playerMap.containsKey(clientFromId)) {
						//nodo
					} else {
						//本服务器发送
						ChatMessage chatMessage = ChatMessage.newBuilder()
								.setChatChannel(1)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						byte[] message = chatMessage.toByteArray();
						for (Map.Entry<String, MessageClient>entry : MessageMapRelation.playerMap.entrySet()) {
							MessageClient mPlayer = entry.getValue();
							mPlayer.playerSocketChannel.write(message);
						}
						log.info("route public send");
					}
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			case 2000:
				//系统转发组频道
				if (clientMessage.length() > 0 && clientGroupId.length() > 0) {
					if (MessageMapRelation.playerMap.containsKey(clientFromId)) {
						//nodo
					} else {
						//本服务器发送
						ChatMessage chatMessage = ChatMessage.newBuilder()
								.setChatChannel(2)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						byte[] message = chatMessage.toByteArray();					
						Set<MessageClient> messagePlayers = MessageMapRelation.groupMap.get(clientGroupId);
						for (MessageClient messagePlayer : messagePlayers) {
							messagePlayer.playerSocketChannel.write(message);
						}
						log.info("route group send");
					}
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			case 3000:
				//系统转发私聊频道
				if (clientMessage.length() > 0 && clientToId.length() > 0) {
					if (MessageMapRelation.playerMap.containsKey(clientFromId)) {
						//nodo
					} else {
						//本服务器发送
						ChatMessage chatMessage = ChatMessage.newBuilder()
								.setChatChannel(3)
								.setFromId(clientFromId)
								.setToId(clientToId)
								.setName(clientName)
								.setGroupId(clientGroupId)
								.setMessage(clientMessage)
								.build();
						byte[] message = chatMessage.toByteArray();					
						for (Map.Entry<String, MessageClient>entry : MessageMapRelation.playerMap.entrySet()) {
							MessageClient mPlayer = entry.getValue();
							if (mPlayer.playerId.equals(clientToId)) {
								mPlayer.playerSocketChannel.write(message);
							}
						}
						log.info("route personal send");
					}
				} else {
					throw new MessageException("error client message");
				}
				break;
				
			default:
				log.error("uncatch chatChannel " + clientChatChannel);
		}
	}
}
