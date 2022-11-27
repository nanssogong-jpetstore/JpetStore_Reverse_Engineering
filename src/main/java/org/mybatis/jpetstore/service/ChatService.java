package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.domain.ChatRoom;
import org.mybatis.jpetstore.domain.ChatRoomUser;
import org.mybatis.jpetstore.mapper.ChatMessageMapper;
import org.mybatis.jpetstore.mapper.ChatRoomMapper;
import org.mybatis.jpetstore.mapper.ChatRoomUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRoomMapper chatRoomMapper;
    private final ChatRoomUserMapper chatRoomUserMapper;
    private final ChatMessageMapper chatMessageMapper;

    public ChatService(ChatRoomMapper chatRoomMapper, ChatRoomUserMapper chatRoomUserMapper, ChatMessageMapper chatMessageMapper) {
        this.chatRoomMapper = chatRoomMapper;
        this.chatRoomUserMapper = chatRoomUserMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    public void makeChatRoom(ChatRoom chatRoom, String receiver) {
        chatRoomMapper.insertChatRoom(chatRoom);
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setRoomId(chatRoom.getRoomId());
        chatRoomUser.setUserId(chatRoom.getUserId());
        chatRoomUserMapper.inviteUser(chatRoomUser);
        chatRoomUser.setUserId(receiver);
        chatRoomUserMapper.inviteUser(chatRoomUser);

    }

    public boolean validateChatRoom(String roomId) {
        return chatRoomMapper.getChatRoom(roomId) == null;
    }

    public List<ChatRoom> getChatRoomList(String userId) {
        return chatRoomMapper.getChatRoomList(userId);
    }


    public void readMessage(ChatMessage chatMessage) {
        ChatRoomUser chatRoomUser = new ChatRoomUser();
        chatRoomUser.setUserId(chatMessage.getSender());
        chatRoomUser.setRoomId(chatMessage.getRoomId());
        chatRoomMapper.readMessage(chatRoomUser);
    }

    public List<ChatMessage> getMessages(String roomId) {
        return chatMessageMapper.getMessages(roomId);
    }

}
