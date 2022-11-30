package org.mybatis.jpetstore.Chat;

import org.mybatis.jpetstore.domain.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    public ChatController(SimpMessageSendingOperations sendingOperations, ChatService chatService) {
        this.sendingOperations = sendingOperations;
        this.chatService = chatService;
    }

    @MessageMapping("/chat/enter")
    public ChatMessage enter(ChatMessage chatMessage) {
        System.out.println("enter method");
        chatService.enterRoom(chatMessage);
        chatService.readMessage(chatMessage);
        sendingOperations.convertAndSend("/topic/chat/room/"+ chatMessage.getRoomId(), chatMessage);

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
        sendingOperations.convertAndSend("/topic/chat/room/"+ chatMessage.getRoomId(), chatMessage);
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
