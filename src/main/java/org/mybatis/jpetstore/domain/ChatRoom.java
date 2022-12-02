package org.mybatis.jpetstore.domain;


import java.io.Serializable;

public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6620528781626504362L;
    private String postId;
    private String roomId;
    private String userId;
    private String roomName;
    private String imgurl;
    private int isRead;
    private String receiver;
    private String content;

    public ChatRoom() {

    }

    public ChatRoom(String postId, String roomId, String userId, String roomName, String imgurl, int isRead) {
        this.postId = postId;
        this.roomId = roomId;
        this.userId = userId;
        this.roomName = roomName;
        this.imgurl = imgurl;
        this.isRead = isRead;
    }

    public String getPostId() { return postId; }

    public void setPostId(String postId) { this.postId = postId; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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

    public String getReceiver() { return receiver; }

    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

}
