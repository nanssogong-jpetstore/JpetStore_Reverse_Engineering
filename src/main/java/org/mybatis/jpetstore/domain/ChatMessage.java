package org.mybatis.jpetstore.domain;


import java.sql.Date;

public class ChatMessage {

    private MessageType type;
    private String roomId;
    private String sender;
    private String receiver;
    private String content;
    private Date createDate;


    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public enum MessageType {
        ENTER("ENTER"),
        TALK("TALK"),
        LEAVE("LEAVE");

        private String value;

        MessageType(String value) {
            this.value=value;
        }

        public static MessageType from(String s) {
            return MessageType.valueOf(s.toUpperCase());
        }
        public String getValue() { return this.value; }
    }
}
