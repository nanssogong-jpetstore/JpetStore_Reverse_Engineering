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
        connect(id, title, name);
    }
</script>

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
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>