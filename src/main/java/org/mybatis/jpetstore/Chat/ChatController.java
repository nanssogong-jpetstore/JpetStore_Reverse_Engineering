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
//        String id = params.get("id").toString();
//        String roomId = params.get("roomId").toString();
//        String message = "";
//        if(ChatMessage.MessageType.ENTER.equals(params.get("type").toString())) {
//            if(messageService.validateChatRoom(id, roomId))
//                message = id + "님이 입장하셨습니다.";
//        }
//
//        if(params.get("message").toString() != null) {
//            message = params.get("message").toString();
//        }
//        sendingOperations.convertAndSend("/topic/chat/room/"+roomId, message);

        /*List<ChatMessage> chatMessageList = chatService.getMessages(chatMessage.getRoomId());
        model.addAttribute("chatMessageList", chatMessageList);*/
        sendingOperations.convertAndSend("/topic/chat/room/"+ chatMessage.getRoomId(), chatMessage);

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

        sendingOperations.convertAndSend("/topic/chat/alarm/"+ chatMessage.getReceiver(), chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat/invite")
    public ChatMessage inviteRoom(ChatMessage chatMessage) {
        System.out.println("Invite User");
        sendingOperations.convertAndSend("/topic/chat/room/"+ chatMessage.getRoomId(), chatMessage);
        return chatMessage;
    }

    @GetMapping("/test")
    public String test() {
        return "ss";
    }
}
