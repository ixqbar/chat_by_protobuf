// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message_java.proto

package com.xingqiba.util;

public final class MessageProtoHandler {
  private MessageProtoHandler() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ChatMessageOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required int32 chatChannel = 1;
    boolean hasChatChannel();
    int getChatChannel();
    
    // optional string fromId = 2 [default = ""];
    boolean hasFromId();
    String getFromId();
    
    // optional string toId = 3 [default = ""];
    boolean hasToId();
    String getToId();
    
    // optional string name = 4 [default = ""];
    boolean hasName();
    String getName();
    
    // optional string groupId = 5 [default = "0"];
    boolean hasGroupId();
    String getGroupId();
    
    // optional string message = 6 [default = ""];
    boolean hasMessage();
    String getMessage();
  }
  public static final class ChatMessage extends
      com.google.protobuf.GeneratedMessage
      implements ChatMessageOrBuilder {
    // Use ChatMessage.newBuilder() to construct.
    private ChatMessage(Builder builder) {
      super(builder);
    }
    private ChatMessage(boolean noInit) {}
    
    private static final ChatMessage defaultInstance;
    public static ChatMessage getDefaultInstance() {
      return defaultInstance;
    }
    
    public ChatMessage getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.xingqiba.util.MessageProtoHandler.internal_static_com_xingqiba_util_ChatMessage_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.xingqiba.util.MessageProtoHandler.internal_static_com_xingqiba_util_ChatMessage_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required int32 chatChannel = 1;
    public static final int CHATCHANNEL_FIELD_NUMBER = 1;
    private int chatChannel_;
    public boolean hasChatChannel() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getChatChannel() {
      return chatChannel_;
    }
    
    // optional string fromId = 2 [default = ""];
    public static final int FROMID_FIELD_NUMBER = 2;
    private java.lang.Object fromId_;
    public boolean hasFromId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public String getFromId() {
      java.lang.Object ref = fromId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          fromId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getFromIdBytes() {
      java.lang.Object ref = fromId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        fromId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional string toId = 3 [default = ""];
    public static final int TOID_FIELD_NUMBER = 3;
    private java.lang.Object toId_;
    public boolean hasToId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getToId() {
      java.lang.Object ref = toId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          toId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getToIdBytes() {
      java.lang.Object ref = toId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        toId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional string name = 4 [default = ""];
    public static final int NAME_FIELD_NUMBER = 4;
    private java.lang.Object name_;
    public boolean hasName() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          name_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional string groupId = 5 [default = "0"];
    public static final int GROUPID_FIELD_NUMBER = 5;
    private java.lang.Object groupId_;
    public boolean hasGroupId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public String getGroupId() {
      java.lang.Object ref = groupId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          groupId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getGroupIdBytes() {
      java.lang.Object ref = groupId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        groupId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional string message = 6 [default = ""];
    public static final int MESSAGE_FIELD_NUMBER = 6;
    private java.lang.Object message_;
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    public String getMessage() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          message_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    private void initFields() {
      chatChannel_ = 0;
      fromId_ = "";
      toId_ = "";
      name_ = "";
      groupId_ = "0";
      message_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasChatChannel()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, chatChannel_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getFromIdBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getToIdBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getNameBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeBytes(5, getGroupIdBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeBytes(6, getMessageBytes());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, chatChannel_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getFromIdBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getToIdBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getNameBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getGroupIdBytes());
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, getMessageBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.xingqiba.util.MessageProtoHandler.ChatMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.xingqiba.util.MessageProtoHandler.ChatMessage prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.xingqiba.util.MessageProtoHandler.ChatMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.xingqiba.util.MessageProtoHandler.internal_static_com_xingqiba_util_ChatMessage_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.xingqiba.util.MessageProtoHandler.internal_static_com_xingqiba_util_ChatMessage_fieldAccessorTable;
      }
      
      // Construct using com.xingqiba.util.MessageProtoHandler.ChatMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        chatChannel_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        fromId_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        toId_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        groupId_ = "0";
        bitField0_ = (bitField0_ & ~0x00000010);
        message_ = "";
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.xingqiba.util.MessageProtoHandler.ChatMessage.getDescriptor();
      }
      
      public com.xingqiba.util.MessageProtoHandler.ChatMessage getDefaultInstanceForType() {
        return com.xingqiba.util.MessageProtoHandler.ChatMessage.getDefaultInstance();
      }
      
      public com.xingqiba.util.MessageProtoHandler.ChatMessage build() {
        com.xingqiba.util.MessageProtoHandler.ChatMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private com.xingqiba.util.MessageProtoHandler.ChatMessage buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        com.xingqiba.util.MessageProtoHandler.ChatMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public com.xingqiba.util.MessageProtoHandler.ChatMessage buildPartial() {
        com.xingqiba.util.MessageProtoHandler.ChatMessage result = new com.xingqiba.util.MessageProtoHandler.ChatMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.chatChannel_ = chatChannel_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.fromId_ = fromId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.toId_ = toId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.groupId_ = groupId_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.message_ = message_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.xingqiba.util.MessageProtoHandler.ChatMessage) {
          return mergeFrom((com.xingqiba.util.MessageProtoHandler.ChatMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.xingqiba.util.MessageProtoHandler.ChatMessage other) {
        if (other == com.xingqiba.util.MessageProtoHandler.ChatMessage.getDefaultInstance()) return this;
        if (other.hasChatChannel()) {
          setChatChannel(other.getChatChannel());
        }
        if (other.hasFromId()) {
          setFromId(other.getFromId());
        }
        if (other.hasToId()) {
          setToId(other.getToId());
        }
        if (other.hasName()) {
          setName(other.getName());
        }
        if (other.hasGroupId()) {
          setGroupId(other.getGroupId());
        }
        if (other.hasMessage()) {
          setMessage(other.getMessage());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasChatChannel()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              chatChannel_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              fromId_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              toId_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              name_ = input.readBytes();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000010;
              groupId_ = input.readBytes();
              break;
            }
            case 50: {
              bitField0_ |= 0x00000020;
              message_ = input.readBytes();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required int32 chatChannel = 1;
      private int chatChannel_ ;
      public boolean hasChatChannel() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getChatChannel() {
        return chatChannel_;
      }
      public Builder setChatChannel(int value) {
        bitField0_ |= 0x00000001;
        chatChannel_ = value;
        onChanged();
        return this;
      }
      public Builder clearChatChannel() {
        bitField0_ = (bitField0_ & ~0x00000001);
        chatChannel_ = 0;
        onChanged();
        return this;
      }
      
      // optional string fromId = 2 [default = ""];
      private java.lang.Object fromId_ = "";
      public boolean hasFromId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public String getFromId() {
        java.lang.Object ref = fromId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          fromId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setFromId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        fromId_ = value;
        onChanged();
        return this;
      }
      public Builder clearFromId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        fromId_ = getDefaultInstance().getFromId();
        onChanged();
        return this;
      }
      void setFromId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000002;
        fromId_ = value;
        onChanged();
      }
      
      // optional string toId = 3 [default = ""];
      private java.lang.Object toId_ = "";
      public boolean hasToId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getToId() {
        java.lang.Object ref = toId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          toId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setToId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        toId_ = value;
        onChanged();
        return this;
      }
      public Builder clearToId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        toId_ = getDefaultInstance().getToId();
        onChanged();
        return this;
      }
      void setToId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        toId_ = value;
        onChanged();
      }
      
      // optional string name = 4 [default = ""];
      private java.lang.Object name_ = "";
      public boolean hasName() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setName(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        name_ = value;
        onChanged();
        return this;
      }
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000008);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      void setName(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000008;
        name_ = value;
        onChanged();
      }
      
      // optional string groupId = 5 [default = "0"];
      private java.lang.Object groupId_ = "0";
      public boolean hasGroupId() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public String getGroupId() {
        java.lang.Object ref = groupId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          groupId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setGroupId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
        groupId_ = value;
        onChanged();
        return this;
      }
      public Builder clearGroupId() {
        bitField0_ = (bitField0_ & ~0x00000010);
        groupId_ = getDefaultInstance().getGroupId();
        onChanged();
        return this;
      }
      void setGroupId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000010;
        groupId_ = value;
        onChanged();
      }
      
      // optional string message = 6 [default = ""];
      private java.lang.Object message_ = "";
      public boolean hasMessage() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      public String getMessage() {
        java.lang.Object ref = message_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          message_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setMessage(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
        message_ = value;
        onChanged();
        return this;
      }
      public Builder clearMessage() {
        bitField0_ = (bitField0_ & ~0x00000020);
        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      void setMessage(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000020;
        message_ = value;
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:com.xingqiba.util.ChatMessage)
    }
    
    static {
      defaultInstance = new ChatMessage(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:com.xingqiba.util.ChatMessage)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_xingqiba_util_ChatMessage_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_xingqiba_util_ChatMessage_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022message_java.proto\022\021com.xingqiba.util\"" +
      "{\n\013ChatMessage\022\023\n\013chatChannel\030\001 \002(\005\022\020\n\006f" +
      "romId\030\002 \001(\t:\000\022\016\n\004toId\030\003 \001(\t:\000\022\016\n\004name\030\004 " +
      "\001(\t:\000\022\022\n\007groupId\030\005 \001(\t:\0010\022\021\n\007message\030\006 \001" +
      "(\t:\000B\025B\023MessageProtoHandler"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_xingqiba_util_ChatMessage_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_xingqiba_util_ChatMessage_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_xingqiba_util_ChatMessage_descriptor,
              new java.lang.String[] { "ChatChannel", "FromId", "ToId", "Name", "GroupId", "Message", },
              com.xingqiba.util.MessageProtoHandler.ChatMessage.class,
              com.xingqiba.util.MessageProtoHandler.ChatMessage.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
