package org.mybatis.jpetstore.Chat;

import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.domain.ChatRoomUser;
import org.mybatis.jpetstore.mapper.ChatMessageMapper;
import org.mybatis.jpetstore.mapper.ChatRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageMapper chatMessageMapper;
    private final ChatRoomMapper chatRoomMapper;

    @Autowired
    public ChatService(ChatMessageMapper chatMessageMapper, ChatRoomMapper chatRoomMapper) {
        this.chatMessageMapper = chatMessageMapper;
        this.chatRoomMapper = chatRoomMapper;
    }

    @Transactional
    public void insertMessage(ChatMessage chatMessage) {
        chatMessageMapper.insertMessage(chatMessage);
    }
}
