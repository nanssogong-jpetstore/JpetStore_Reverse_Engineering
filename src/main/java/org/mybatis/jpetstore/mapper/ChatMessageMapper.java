package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.ChatMessage;

import java.util.List;

public interface ChatMessageMapper {
    void insertMessage(ChatMessage chatMessage);
    List<ChatMessage> getMessages(String roomId);
}
