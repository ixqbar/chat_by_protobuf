package  {
	import com.netease.protobuf.*;
	import com.netease.protobuf.fieldDescriptors.*;
	import flash.utils.Endian;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	import flash.errors.IOError;
	// @@protoc_insertion_point(imports)

	// @@protoc_insertion_point(class_metadata)
	public dynamic final class ChatMessage extends com.netease.protobuf.Message {
		public static const CHATCHANNEL:FieldDescriptor$TYPE_INT32 = new FieldDescriptor$TYPE_INT32("ChatMessage.chatChannel", "chatChannel", (1 << 3) | com.netease.protobuf.WireType.VARINT);

		public var chatChannel:int;

		public static const FROMID:FieldDescriptor$TYPE_STRING = new FieldDescriptor$TYPE_STRING("ChatMessage.fromId", "fromId", (2 << 3) | com.netease.protobuf.WireType.LENGTH_DELIMITED);

		private var fromId$field:String;

		public function clearFromId():void {
			fromId$field = null;
		}

		public function get hasFromId():Boolean {
			return fromId$field != null;
		}

		public function set fromId(value:String):void {
			fromId$field = value;
		}

		public function get fromId():String {
			if(!hasFromId) {
				return "";
			}
			return fromId$field;
		}

		public static const TOID:FieldDescriptor$TYPE_STRING = new FieldDescriptor$TYPE_STRING("ChatMessage.toId", "toId", (3 << 3) | com.netease.protobuf.WireType.LENGTH_DELIMITED);

		private var toId$field:String;

		public function clearToId():void {
			toId$field = null;
		}

		public function get hasToId():Boolean {
			return toId$field != null;
		}

		public function set toId(value:String):void {
			toId$field = value;
		}

		public function get toId():String {
			if(!hasToId) {
				return "";
			}
			return toId$field;
		}

		public static const NAME:FieldDescriptor$TYPE_STRING = new FieldDescriptor$TYPE_STRING("ChatMessage.name", "name", (4 << 3) | com.netease.protobuf.WireType.LENGTH_DELIMITED);

		private var name$field:String;

		public function clearName():void {
			name$field = null;
		}

		public function get hasName():Boolean {
			return name$field != null;
		}

		public function set name(value:String):void {
			name$field = value;
		}

		public function get name():String {
			if(!hasName) {
				return "";
			}
			return name$field;
		}

		public static const GROUPID:FieldDescriptor$TYPE_STRING = new FieldDescriptor$TYPE_STRING("ChatMessage.groupId", "groupId", (5 << 3) | com.netease.protobuf.WireType.LENGTH_DELIMITED);

		private var groupId$field:String;

		public function clearGroupId():void {
			groupId$field = null;
		}

		public function get hasGroupId():Boolean {
			return groupId$field != null;
		}

		public function set groupId(value:String):void {
			groupId$field = value;
		}

		public function get groupId():String {
			if(!hasGroupId) {
				return "0";
			}
			return groupId$field;
		}

		public static const MESSAGE:FieldDescriptor$TYPE_STRING = new FieldDescriptor$TYPE_STRING("ChatMessage.message", "message", (6 << 3) | com.netease.protobuf.WireType.LENGTH_DELIMITED);

		private var message$field:String;

		public function clearMessage():void {
			message$field = null;
		}

		public function get hasMessage():Boolean {
			return message$field != null;
		}

		public function set message(value:String):void {
			message$field = value;
		}

		public function get message():String {
			if(!hasMessage) {
				return "";
			}
			return message$field;
		}

		/**
		 *  @private
		 */
		override public final function writeToBuffer(output:com.netease.protobuf.WritingBuffer):void {
			com.netease.protobuf.WriteUtils.writeTag(output, com.netease.protobuf.WireType.VARINT, 1);
			com.netease.protobuf.WriteUtils.write$TYPE_INT32(output, this.chatChannel);
			if (hasFromId) {
				com.netease.protobuf.WriteUtils.writeTag(output, com.netease.protobuf.WireType.LENGTH_DELIMITED, 2);
				com.netease.protobuf.WriteUtils.write$TYPE_STRING(output, fromId$field);
			}
			if (hasToId) {
				com.netease.protobuf.WriteUtils.writeTag(output, com.netease.protobuf.WireType.LENGTH_DELIMITED, 3);
				com.netease.protobuf.WriteUtils.write$TYPE_STRING(output, toId$field);
			}
			if (hasName) {
				com.netease.protobuf.WriteUtils.writeTag(output, com.netease.protobuf.WireType.LENGTH_DELIMITED, 4);
				com.netease.protobuf.WriteUtils.write$TYPE_STRING(output, name$field);
			}
			if (hasGroupId) {
				com.netease.protobuf.WriteUtils.writeTag(output, com.netease.protobuf.WireType.LENGTH_DELIMITED, 5);
				com.netease.protobuf.WriteUtils.write$TYPE_STRING(output, groupId$field);
			}
			if (hasMessage) {
				com.netease.protobuf.WriteUtils.writeTag(output, com.netease.protobuf.WireType.LENGTH_DELIMITED, 6);
				com.netease.protobuf.WriteUtils.write$TYPE_STRING(output, message$field);
			}
			for (var fieldKey:* in this) {
				super.writeUnknown(output, fieldKey);
			}
		}

		/**
		 *  @private
		 */
		override public final function readFromSlice(input:flash.utils.IDataInput, bytesAfterSlice:uint):void {
			var chatChannel$count:uint = 0;
			var fromId$count:uint = 0;
			var toId$count:uint = 0;
			var name$count:uint = 0;
			var groupId$count:uint = 0;
			var message$count:uint = 0;
			while (input.bytesAvailable > bytesAfterSlice) {
				var tag:uint = com.netease.protobuf.ReadUtils.read$TYPE_UINT32(input);
				switch (tag >> 3) {
				case 1:
					if (chatChannel$count != 0) {
						throw new flash.errors.IOError('Bad data format: ChatMessage.chatChannel cannot be set twice.');
					}
					++chatChannel$count;
					this.chatChannel = com.netease.protobuf.ReadUtils.read$TYPE_INT32(input);
					break;
				case 2:
					if (fromId$count != 0) {
						throw new flash.errors.IOError('Bad data format: ChatMessage.fromId cannot be set twice.');
					}
					++fromId$count;
					this.fromId = com.netease.protobuf.ReadUtils.read$TYPE_STRING(input);
					break;
				case 3:
					if (toId$count != 0) {
						throw new flash.errors.IOError('Bad data format: ChatMessage.toId cannot be set twice.');
					}
					++toId$count;
					this.toId = com.netease.protobuf.ReadUtils.read$TYPE_STRING(input);
					break;
				case 4:
					if (name$count != 0) {
						throw new flash.errors.IOError('Bad data format: ChatMessage.name cannot be set twice.');
					}
					++name$count;
					this.name = com.netease.protobuf.ReadUtils.read$TYPE_STRING(input);
					break;
				case 5:
					if (groupId$count != 0) {
						throw new flash.errors.IOError('Bad data format: ChatMessage.groupId cannot be set twice.');
					}
					++groupId$count;
					this.groupId = com.netease.protobuf.ReadUtils.read$TYPE_STRING(input);
					break;
				case 6:
					if (message$count != 0) {
						throw new flash.errors.IOError('Bad data format: ChatMessage.message cannot be set twice.');
					}
					++message$count;
					this.message = com.netease.protobuf.ReadUtils.read$TYPE_STRING(input);
					break;
				default:
					super.readUnknown(input, tag);
					break;
				}
			}
		}

	}
}
