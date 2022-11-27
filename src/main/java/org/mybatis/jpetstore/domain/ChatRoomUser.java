package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class ChatRoomUser  implements Serializable {

    private static final long serialVersionUID = 6620528781626504362L;
    private String roomId;
    private String userId;
    private int isRead;


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

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
