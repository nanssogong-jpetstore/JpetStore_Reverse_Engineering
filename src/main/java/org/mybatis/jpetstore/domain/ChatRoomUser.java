package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class ChatRoomUser  implements Serializable {

    private static final long serialVersionUID = 6620528781626504362L;
    private String roomId;
    private String userId;
    private String receiver;
    private int isRead;
    private int status;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
