package org.mybatis.jpetstore.web.actions;


import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import org.mybatis.jpetstore.domain.ChatMessage;
import org.mybatis.jpetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@SessionScope
public class ChatActionBean extends AbstractActionBean {
    private static final long serialVersionUID = -7713371873197427738L;

    private static final String CHAT = "/WEB-INF/jsp/chat/chat.jsp";
    private static final String CHATLIST = "/WEB-INF/jsp/chat/chatList.jsp";

    @SpringBean
    private transient AccountService accountService;

    private String username;
    private String title;
    private String name;


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
    public void setName() { this.name = name; }


    @Validate(required = true, on = { "signon", "newAccount", "editAccount" })
    public void setUsername(String username) {
        this.username = username;
    }


    public ForwardResolution chatStart() {
        System.out.println("userName " + username);
        System.out.println("title " + title);
        return new ForwardResolution(CHAT);
    }

    public ForwardResolution chatList() {
        return new ForwardResolution(CHATLIST);
    }

    @GetMapping("hello")
    public void hello() {
        System.out.println("hello");
    }

}
