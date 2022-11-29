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
    <h6>작성자: ${actionBean.animalMating.userId}

      <stripes:link class="Button" onclick="window.open(this.href, '', 'width=400, height=700, left=700, top=100'); return false;"
                                                             beanclass="org.mybatis.jpetstore.web.actions.ChatActionBean"
                                                             event="chatStart">
      <stripes:param name="username" value="${sessionScope.accountBean.account.username}" />
      <stripes:param name="title" value="${actionBean.animalMating.title}" />
      <stripes:param name="name" value="${actionBean.animalMating.userId}" />
      <stripes:param name="id" value="${actionBean.animalMating.id}" />
        <stripes:param name="sex" value="${actionBean.animalMating.sex}" />
      <stripes:param name="imgurl" value="${actionBean.animalMating.imgUrl}" />

      Chat Start
    </stripes:link></h6>
    작성일: ${actionBean.animalMating.createdate} <br>
    조회수 : ${actionBean.animalMating.view}
  </div>
  <div class="article_container">
    성별 : ${actionBean.animalMating.sex} <br>
    성격 : ${actionBean.animalMating.characters}
    <hr>
    <br>
    ${actionBean.animalMating.contents}<br><br>
    <img id="deimg" style="height:300px; width: 300px;" src="${actionBean.animalMating.imgUrl}" alt="..." />
    <br><br>
  </div>


</div>
</body>
</html>

<%@ include file="../common/IncludeBottom.jsp"%>