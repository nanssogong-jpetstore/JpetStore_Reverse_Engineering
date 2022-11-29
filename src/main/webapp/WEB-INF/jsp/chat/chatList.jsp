<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: zxz46
  Date: 2022-11-21
  Time: 오후 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>${actionBean.title} 대화방</title>
    <link rel="stylesheet" href="../css/main.css" />
</head>

<body>
<div id="chat-page">
    <div class="chat-container">
        <div class="chat-header">
            <h2>나의 채팅 목록</h2>
        </div>

        <ul id="messageArea">
            <c:forEach var="chat" items="${actionBean.chatRoomList}">
                    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.ChatActionBean" style="text-decoration: none;"
                                  event="chatStart">
                        <stripes:param name="username" value="${sessionScope.accountBean.account.username}" />
                        <stripes:param name="title" value="${chat.roomName}" />
                        <stripes:param name="roomId" value="${chat.roomId}" />
                        <stripes:param name="name" value="${chat.userId}" />
                        <stripes:param name="id" value="${chat.postId}" />
                        <stripes:param name="imgurl" value="${chat.imgurl}" />
                <div class="chat-messageParent">
                    <li class="chat-message" style="border-bottom: 1px solid #ececec;">
                        <img style="position:absolute;" src="${chat.imgurl}">
                        <span>${chat.roomName}</span>
                        <p>${chat.content}</p>
                    </li>
                </div>
                </stripes:link>
            </c:forEach>
        </ul>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>