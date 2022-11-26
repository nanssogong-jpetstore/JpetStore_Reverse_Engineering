<%--
  Created by IntelliJ IDEA.
  User: 승현
  Date: 2022-11-26
  Time: 오전 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>
<html>
<head>
  <%--<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />--%>
  <link rel="StyleSheet" href="../css/styles.css" type="text/css" media="screen"/>
</head>

<body>
<div class="ArticleContentBox" style="padding: 29px 29px 0;">
  <div class="article_header" style="position: relative; margin-bottom: 20px; padding-bottom: 20px; border-bottom: 1px solid gray;">
    <h2>${actionBean.animalMating.title}</h2>
    <h6>작성자: ${actionBean.animalMating.userId} <button>1:1채팅</button></h6>
    작성일: ${actionBean.animalMating.createdate}
  </div>
  <div class="article_container">
    성격 : ${actionBean.animalMating.characters}
    <hr>
    <br>
    ${actionBean.animalMating.contents}<br><br>
    <img id="deimg" style="height:70%;" src="${actionBean.animalMating.imgUrl}" alt="..." />
    <br><br>
  </div>


</div>
</body>
</html>

<%@ include file="../common/IncludeBottom.jsp"%>