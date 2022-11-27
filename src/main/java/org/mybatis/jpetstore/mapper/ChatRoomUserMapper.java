package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.ChatRoomUser;

public interface ChatRoomUserMapper {

    void inviteUser(ChatRoomUser chatRoomUser);
}
