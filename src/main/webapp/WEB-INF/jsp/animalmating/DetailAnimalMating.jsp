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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<script>
  $(Document).ready(function () {
    const boardId = '${actionBean.animalMatingDetail.id}';
    const userId = "${sessionScope.accountBean.account.username}";

    $('#likebtn').click(function(){
    $.ajax({
      url : "/jpetstore_war/like",
      type : 'POST',
      contentType: 'application/json',
      data : JSON.stringify({
        "boardId" : boardId,
        "userId" : userId
      }),
      success : function(data){
        if("${actionBean.animalMatingDetail.like_check}"== 1){
          $('#likecheck').val(0);
          $('#likebtn').attr('class','btn btn-danger');

        }else if("${actionBean.animalMatingDetail.like_check}"== 0){
          $('#likecheck').val(1);
          $('#likebtn').attr('class','btn btn-light');
        }
      }, error : function(result){
            alert("좋아요 중 오류 발생");
      }, complete:function(){
        reloadDivArea();
      }
    });
    });
    function reloadDivArea() {
      $.ajax({
        url : "/jpetstore_war/likeCheck",
        type : 'POST',
        contentType: 'application/json',
        data : JSON.stringify({
          "boardId" : boardId,
          "userId" : userId
        }),
        success : function(data){
          var likeCheck=data.likeCheck;
          if(likeCheck=="1"){
            $('#likebtn').attr('class','btn btn-danger');
          }else if(likeCheck=="0"){
            $('#likebtn').attr('class','btn btn-light');
          }
        }, error : function(result){
          alert("좋아요 중 오류 발생");
        }
  });
    }
  });
</script>
<body>
<div class="ArticleContentBox" style="padding: 29px 29px 0; width: 1000px; margin : 0 auto;">
  <div class="article_header" style="position: relative; margin-bottom: 20px; padding-bottom: 20px; border-bottom: 1px solid gray;">
    <h2>${actionBean.animalMatingDetail.title}</h2>
    <h6>작성자: ${actionBean.animalMatingDetail.userId}

      <c:if test="${sessionScope.accountBean.account.username ne actionBean.animalMatingDetail.userId}">
      <stripes:link class="Button" onclick="window.open(this.href, '', 'width=400, height=700, left=700, top=100'); return false;"
                                                             beanclass="org.mybatis.jpetstore.web.actions.ChatActionBean"
                                                             event="initChat">
        <stripes:param name="username" value="${sessionScope.accountBean.account.username}" />
        <stripes:param name="title" value="${actionBean.animalMatingDetail.title}" />
        <stripes:param name="name" value="${actionBean.animalMatingDetail.userId}" />
        <stripes:param name="id" value="${actionBean.animalMatingDetail.id}" />
        <stripes:param name="sex" value="${actionBean.animalMatingDetail.sex}" />
        <stripes:param name="imgurl" value="${actionBean.animalMatingDetail.imgUrl}" />
        <stripes:param name="roomId" value="empty" />
        <stripes:param name="status" value="${actionBean.animalMatingDetail.status}" />
      Chat Start
      </stripes:link>
      </c:if>
      <c:if test="${sessionScope.accountBean.account.username eq actionBean.animalMatingDetail.userId}">
      <stripes:link class="Button" beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                    event="editAnimalMatingView">
        <stripes:param name="btnUpdete" value="${actionBean.animalMating.id}" />UPDATE
      </stripes:link>
        <stripes:link class="Button" beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                    event="userDeleteBtnSet">
          <stripes:param name="btnDelete" value="${actionBean.animalMating.id}" />DELETE
        </stripes:link>
      </c:if>
    </h6>
    작성일: ${actionBean.animalMatingDetail.createdate} <br>
    조회수 : ${actionBean.animalMatingDetail.view}<br>
    <c:set var="status" value="${actionBean.animalMatingDetail.status}" />
    <c:if test="${status eq 'RESERVED'}">
      상태 : <span style="background-color:#ffc720; color:black"> <b>Reserved</b> </span>
    </c:if>
    <c:if test="${status eq 'COMPLETED'}">
      상태 : <span style="background-color:#495057; color:white"> <b>Completed</b> </span>
    </c:if>
  </div>
    <td id="like">
      <c:choose>
        <c:when test="${actionBean.animalMatingDetail.like_check==0}">
          <button type="button" class="btn btn-light" id="likebtn">좋아요</button>
            <input type="hidden" id="likecheck" value="${actionBean.animalMatingDetail.like_check}">
        </c:when>
        <c:when test="${actionBean.animalMatingDetail.like_check==1}">
            <button type="button" class="btn btn-danger" id="likebtn">좋아요</button>
            <input type="hidden" id="likecheck" value="${actionBean.animalMatingDetail.like_check}">

        </c:when>
      </c:choose>
    </td>
  <div class="article_container">
    성별 : ${actionBean.animalMatingDetail.sex} <br>
    성격 : <c:forEach var="characters" items="${actionBean.animalMatingCha}"> ${characters} | </c:forEach>
    <hr>
    <br>
    ${actionBean.animalMatingDetail.characters} <br><br>
    ${actionBean.animalMatingDetail.contents}<br><br>
    <img id="deimg" style="height:300px; width: 300px;" src="${actionBean.animalMatingDetail.imgUrl}" alt="..." />
    <br><br>
  </div>
</div>
</body>
</html>

<%@ include file="../common/IncludeBottom.jsp"%>
