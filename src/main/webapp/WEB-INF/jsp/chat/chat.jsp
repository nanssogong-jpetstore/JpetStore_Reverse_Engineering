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
<script>
    window.onload = function() {
        var id = '${actionBean.username}';
        var title = '${actionBean.title}';
        var name = '${actionBean.name}';
        var matingId = '${actionBean.id}';
        var roomId = '${actionBean.roomId}';
        if(roomId === 'empty') {
            roomId = title + id;
        }
        connect(id, roomId, name);
    }

    function getAvatarColor(messageSender) {
        var hash = 0;
        for( var i = 0; i < messageSender.length; i++) {
            hash = 31 * hash + messageSender.charCodeAt(i);
        }
        var index = Math.abs(hash % colors.length);
        return colors[index];
    }
</script>
<style>
    a.Button, a.Button:link, a.Button:visited {
        padding: .3ex;
        color: #fff;
        background-color: #005e21;
        text-decoration: none;
        font-family: helvetica, tahoma, arial, verdana, sans-serif;
        font-size: 1.5ex;
    }

    a.Button:hover {
        color: #000;
        background-color: #54c07a;
    }

</style>
<body>
<div id="chat-page" class="hidden">
    <div class="chat-container">
        <div class="chat-header">
            <h2>${actionBean.title} 교배 룸</h2>
        </div>
        <div class="connecting">
            연결중...
        </div>
        <ul id="messageArea">
            <c:forEach var="chatlist" items="${actionBean.chatMessageList}">
                <c:choose>
                    <c:when test="${chatlist.sender eq sessionScope.accountBean.account.username}">
                        <div class="chat-messageParent" style="text-align: right;">
                            <li class="chat-message">
                                <span>${chatlist.sender}</span>
                                    <i style="background-color: #2196F3; position: relative;">${actionBean.firstName_sender}</i>
                                <p>${chatlist.content}</p>
                            </li>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="chat-messageParent">
                            <li class="chat-message">
                                <span>${chatlist.sender}</span>
                                <i style="background-color: #ff5652; position: absolute;">${actionBean.firstName_receiver}</i>
                                <p>${chatlist.content}</p>
                            </li>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
        <form id="messageForm" name="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">보내기</button>
                </div>
            </div>
        </form>
    </div>
    <div>
        <h6>
        <stripes:link class="Button"
                      beanclass="org.mybatis.jpetstore.web.actions.ChatActionBean"
                      event="chatList">
            <stripes:param name="username" value="${sessionScope.accountBean.account.username}" />
            Go To Back
        </stripes:link>
        </h6>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>