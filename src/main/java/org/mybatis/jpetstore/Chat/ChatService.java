package org.mybatis.jpetstore.Chat;

import org.mybatis.jpetstore.domain.BoardLike;
import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.domain.ChatRoomUser;
import org.mybatis.jpetstore.mapper.ChatMessageMapper;
import org.mybatis.jpetstore.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {

    private final ChatMapper chatMapper;

    @Autowired
    public ChatService(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    @Transactional
    public void insertMessage(ChatMessage chatMessage) {
        chatMapper.insertMessage(chatMessage);
    }

    @Transactional
    public void enterRoom(ChatMessage chatMessage) {
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setRoomId(chatMessage.getRoomId());
        chatRoomUser.setUserId(chatMessage.getSender());
        chatMapper.enterRoom(chatRoomUser);
    }
    public void exitRoom(ChatMessage chatMessage) {
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setRoomId(chatMessage.getRoomId());
        chatRoomUser.setUserId(chatMessage.getSender());
        chatMapper.exitRoom(chatRoomUser);
    }

    public ChatRoomUser getReceiverStatus(ChatMessage chatMessage) {
        return chatMapper.getChatRoomUser(chatMessage);
    }

    public void readMessage(ChatMessage chatMessage) {
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setUserId(chatMessage.getSender());
        chatRoomUser.setRoomId(chatMessage.getRoomId());
        chatMapper.readMessage(chatRoomUser);
    }

    public void noneReadMessage(ChatMessage chatMessage) {
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setUserId(chatMessage.getReceiver());
        chatRoomUser.setRoomId(chatMessage.getRoomId());
        chatMapper.noneReadMessage(chatRoomUser);
    }



}
