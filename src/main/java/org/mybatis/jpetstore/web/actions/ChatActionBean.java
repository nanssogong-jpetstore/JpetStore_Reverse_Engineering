package org.mybatis.jpetstore.web.actions;


import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.domain.ChatRoom;
import org.mybatis.jpetstore.service.AccountService;
import org.mybatis.jpetstore.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SessionScope
public class ChatActionBean extends AbstractActionBean {
    private static final long serialVersionUID = -7713371873197427738L;

    private static final String CHAT = "/WEB-INF/jsp/chat/chat.jsp";
    private static final String CHATLIST = "/WEB-INF/jsp/chat/chatList.jsp";

    @SpringBean
    private transient AccountService accountService;

    private String username; //로그인한 유저
    private String title; //교배게시판 제목
    private String name; //receiver
    private String id; //교배 게시물 번호
    private String imgurl;
    private String roomId;
    private String firstName_sender;
    private String firstName_receiver;

    private ChatRoom chatRoom;
    private List<ChatRoom> chatRoomList;
    private ChatMessage chatMessage;
    private List<ChatMessage> chatMessageList;

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUsername() {
        return username;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getImgurl() { return imgurl; }
    public void setImgurl(String imgurl) { this.imgurl = imgurl; }

    public String getFirstName_sender() { return firstName_sender; }
    public void setFirstName_sender(String firstName_receiver) { this.firstName_receiver = firstName_receiver; }

    public String getFirstName_receiver() { return firstName_receiver; }
    public void setFirstName_receiver(String firstName_receiver) { this.firstName_receiver = firstName_receiver; }

    public ChatRoom getChatRoom() { return chatRoom; }
    public void setChatRoom(ChatRoom chatRoom) { this.chatRoom = chatRoom; }

    public List<ChatRoom> getChatRoomList() { return chatRoomList; }
    public void setChatRoomList(List<ChatRoom> chatRoomList) { this.chatRoomList = chatRoomList; }

    public ChatMessage getChatMessage() { return chatMessage; }
    public void setChatMessage(ChatMessage chatMessage) { this.chatMessage = chatMessage; }
    
    public List<ChatMessage> getChatMessageList() { return chatMessageList; }
    public void setChatMessageList(List<ChatMessage> chatMessageList) { this.chatMessageList = chatMessageList; }

    @Validate(required = true, on = { "signon", "newAccount", "editAccount" })
    public void setUsername(String username) {
        this.username = username;
    }

    @SpringBean
    private transient ChatService chatService;


    public ForwardResolution initChat() {
        System.out.println("target user : " + name);
        String chatRoomId = title+username;

        if(chatService.validateChatRoom(chatRoomId)) {
            ChatRoom chatRoom = new ChatRoom(id, chatRoomId, username, title, imgurl, 1);
            chatService.makeChatRoom(chatRoom, name);
        }
        firstName_sender = getFirstName(username);
        firstName_receiver = getFirstName(name);
        ChatMessage chatMessage = new ChatMessage(ChatMessage.MessageType.ENTER, chatRoomId, username, name, null);
        //chatService.readMessage(chatMessage);
        chatMessageList = chatService.getMessages(chatRoomId);
        return new ForwardResolution(CHAT);
    }

    public ForwardResolution chatStart() {
        System.out.println("id = " + id);
        System.out.println("userName " + username);
        System.out.println("title " + title);
        firstName_sender = getFirstName(username);
        firstName_receiver = getFirstName(name);
        chatMessageList = chatService.getMessages(roomId);

        return new ForwardResolution(CHAT);
    }

    public ForwardResolution chatList() {
        chatRoomList = chatService.getChatRoomList(username);
        return new ForwardResolution(CHATLIST);
    }

    @GetMapping("hello")
    public void hello() {
        System.out.println("hello");
    }

    private String getFirstName(String name) {
        return String.valueOf(name.toUpperCase().charAt(0));
    }
}
