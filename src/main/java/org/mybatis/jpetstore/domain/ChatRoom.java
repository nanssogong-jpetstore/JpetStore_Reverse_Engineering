package org.mybatis.jpetstore.domain;

import java.util.UUID;

public class ChatRoom {

    private String roomId;
    private String roomName;
    private int isRead;

    public ChatRoom() {

    }
    public ChatRoom(String roomId, String roomName, int isRead) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.isRead = isRead;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = name;
        return room;
    }

}
