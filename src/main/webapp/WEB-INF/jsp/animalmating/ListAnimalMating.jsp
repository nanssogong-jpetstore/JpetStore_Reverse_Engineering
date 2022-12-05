<%@ include file="../common/IncludeTop.jsp"%>
<head>
<%--<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />--%>
<link rel="StyleSheet" href="../css/styles.css" type="text/css" media="screen"/>
</head>
<div id="Catalog">
    <div class="container px-4 px-lg-5 mt-5">
        <h2>Animal Mating</h2>
        <hr>
            <div id = "SearchMating"><stripes:form
                beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean">
                <stripes:select name="searchOption" value="${searchOption}">
                    <stripes:options-collection collection="${actionBean.searchOptionList}"  />
                </stripes:select>
                <stripes:text name="keyword" size="14" />
                <stripes:submit name="searchMating" value="Search" />
                </stripes:form>
        </div>
        <br/>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <c:forEach var="mating" items="${actionBean.animalMatingList}">
                <div class="col mb-5">
                    <div class="card h-100" style="box-shadow: 5px 5px 5px 5px gray;">
                        <!-- Product image-->
                        <c:choose>
                            <c:when test="${mating.status eq 'COMPLETED'}">
                                <img id="Img" style="height:70%;" class="card-img-top" src="${mating.imgUrl}" alt="..." />
                                <span style="background-color:#495057; color:white">
                                    <b>Completed</b>
                                </span>
                            </c:when>
                            <c:when test="${mating.status eq 'RESERVED'}">
                                <img id="Img" style="height:70%;" class="card-img-top" src="${mating.imgUrl}" alt="..." />
                                <span style="background-color:#ffc720; color:black">
                                    <b>Reserved</b>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <img id="Img" style="height:70%;" class="card-img-top" src="${mating.imgUrl}" alt="..." />
                                <span style="background-color:#146c43; color:black">
                                    <b>Before Matching</b>
                                </span>
                            </c:otherwise>
                        </c:choose>

                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder">${mating.categoryid}</h5>
                                <h6 class="fw-bolder">TITLE : ${mating.title}</h6>
                                <!-- Product price-->
                                <b>GENDER:</b>${mating.sex}
                                <b>	&#60;CHARACTER&#62;<br></b> ${mating.animal_characters}
                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center">
                                <stripes:link class="btn btn-outline-dark mt-auto" href="#"
                                        beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                                        event="getMatingInfo">
                                <stripes:param name="id" value="${mating.id}" />
                                INFORMATION
                                </stripes:link>
                                <%--<a class="btn btn-outline-dark mt-auto" href="#">INFOMATION</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

                    <!-- Product actions-->
        </div>
    </div>

    <div class="page">
        <ul class="paging">

            <li> <stripes:link class="arrow left"
                               beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                               event="paging">
                <stripes:param name="cpage" value="${actionBean.preBlock}" />
                &lt;&lt;
            </stripes:link></li>
            <c:forEach var="i" begin="1" end="${ actionBean.pageCount }" step="1">
                <li>
                    <c:choose>
                        <c:when test="${i == actionBean.cpage}">
                            <stripes:link class="num" style="color : #000; font-weight: bold;"
                                          beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                                          event="paging">
                                <stripes:param name="cpage" value="${i}" />
                                ${i}
                            </stripes:link>
                        </c:when>
                        <c:otherwise>
                            <stripes:link class="num"
                                          beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                                          event="paging">
                                <stripes:param name="cpage" value="${i}" />
                                ${i}
                            </stripes:link>
                        </c:otherwise>

                    </c:choose>
                </li>
            </c:forEach>
            <li> <stripes:link class="arrow right"
                               beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                               event="paging">
                <stripes:param name="cpage" value="${actionBean.nextBlock}" />
                &gt;&gt;
            </stripes:link></li>

        </ul>
    </div>

    <div style="position: absolute; top:75px; right: 40%;">
        <stripes:link class="Button"
                      beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                      event="addAnimalMatingView">
            AddAnimal
        </stripes:link>
    </div>
    <c:choose>
      <c:when test="${actionBean.code eq 'all'}">
            <div style="position: absolute; top:75px; left: 40%;">
                <stripes:link class="Button"
                              beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                              event="recommendAnimalMating">
                    <stripes:param name="code" value="recomm" />
                    RecommendAnimal
                </stripes:link>
            </div>
      </c:when>
      <c:otherwise>
          <div style="position: absolute; top:75px; left: 40%;">
              <stripes:link class="Button"
                            beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                            event="listAnimalAccount">
                  <stripes:param name="code" value="all" />
                  AllAnimal
              </stripes:link>
          </div>
      </c:otherwise>

    </c:choose>



</div>


<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="../../js/scripts.js"></script>



<%@ include file="../common/IncludeBottom.jsp"%>
