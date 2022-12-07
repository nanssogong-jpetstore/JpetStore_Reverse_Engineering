package org.mybatis.jpetstore.Chat;

import org.mybatis.jpetstore.domain.BoardLike;
import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.service.AnimalService;
import org.mybatis.jpetstore.web.actions.AnimalActionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;
    private Logger logger = LoggerFactory.getLogger(ChatController.class);


    public ChatController(SimpMessageSendingOperations sendingOperations, ChatService chatService) {
        this.sendingOperations = sendingOperations;
        this.chatService = chatService;
    }

    @MessageMapping("/chat/enter")
    public ChatMessage enter(ChatMessage chatMessage) {
        System.out.println("enter method");
        chatService.enterRoom(chatMessage);
        chatService.readMessage(chatMessage);
        sendingOperations.convertAndSend("/queue/chat/room/"+ chatMessage.getRoomId(), chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat/exit")
    public ChatMessage exit(ChatMessage chatMessage) {
        chatService.exitRoom(chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat/message")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatService.insertMessage(chatMessage);
        sendingOperations.convertAndSend("/queue/chat/room/"+ chatMessage.getRoomId(), chatMessage);
        return chatMessage;
    }

    @MessageMapping("/alarm/message")
    public ChatMessage messageGreeting(ChatMessage chatMessage) {
        chatMessage.setStatus(chatService.getReceiverStatus(chatMessage).getStatus());
        if(chatMessage.getStatus() == 0) {
            chatService.noneReadMessage(chatMessage);
            sendingOperations.convertAndSend("/topic/chat/alarm/"+ chatMessage.getReceiver(), chatMessage);
        }

        return chatMessage;
    }



}
