package org.mybatis.jpetstore.Chat;

import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.mapper.ChatMessageMapper;
import org.mybatis.jpetstore.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
