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
            <div class="chat-messageParent">
                <li class="chat-message" style="border-bottom: 1px solid #ececec;">
                    <img style="position:absolute;" src="https://jpet-img.s3.ap-northeast-2.amazonaws.com/3728e44a-48e2-468a-bd69-645326b90118.jpg">
                    <span>ACID</span>
                    <p>ㅎ2</p>
                </li>
            </div>
            <div class="chat-messageParent">
                <li class="chat-message" style="border-bottom: 1px solid #ececec;">
                    <img style="position:absolute;" src="	https://jpet-img.s3.ap-northeast-2.amazonaws.com/2b91b771-8e8b-4dc0-8392-e0c9a05db1df.png">
                    <span>ACID</span>
                    <p>ㅎ2</p>
                </li>
            </div>
            <div class="chat-messageParent">
                <li class="chat-message" style="border-bottom: 1px solid #ececec;">
                        <img style="position:absolute;" src="https://jpet-img.s3.ap-northeast-2.amazonaws.com/d3e92b8b-0fda-45b9-bbac-61c516ad01ce.png">
                    <span>ACID</span>
                    <p>ㅎ2</p>
                </li>
            </div>


        </ul>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="../js/main.js"></script>
</body>
</html>