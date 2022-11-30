package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.domain.ChatRoom;
import org.mybatis.jpetstore.domain.ChatRoomUser;
import org.mybatis.jpetstore.mapper.ChatMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatMapper chatMapper;

    public ChatService(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    public void makeChatRoom(ChatRoom chatRoom, String receiver) {
        chatMapper.insertChatRoom(chatRoom);
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setRoomId(chatRoom.getRoomId());
        chatRoomUser.setUserId(chatRoom.getUserId());
        chatRoomUser.setReceiver(receiver);
        chatMapper.inviteUser(chatRoomUser);
        chatRoomUser.setUserId(receiver);
        chatRoomUser.setReceiver(chatRoom.getUserId());
        chatMapper.inviteUser(chatRoomUser);

    }

    public boolean validateChatRoom(String roomId) {
        return chatMapper.getChatRoom(roomId) == null;
    }

    public List<ChatRoom> getChatRoomList(String userId) {
        return chatMapper.getChatRoomList(userId);
    }


    public void readMessage(ChatMessage chatMessage) {
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setUserId(chatMessage.getSender());
        chatRoomUser.setRoomId(chatMessage.getRoomId());
        chatMapper.readMessage(chatRoomUser);
    }

    public List<ChatMessage> getMessages(String roomId) {
        return chatMapper.getMessages(roomId);
    }

}
