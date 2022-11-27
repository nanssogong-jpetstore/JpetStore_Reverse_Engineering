package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.ChatRoom;
import org.mybatis.jpetstore.domain.ChatRoomUser;

import java.util.List;

public interface ChatRoomMapper {
    void insertChatRoom(ChatRoom chatRoom);
    ChatRoom getChatRoom(String roomId);
    List<ChatRoom> getChatRoomList(String userId);
    void readMessage(ChatRoomUser chatRoomUser);
    void noneReadMessage(ChatRoomUser chatRoomUser);
}
