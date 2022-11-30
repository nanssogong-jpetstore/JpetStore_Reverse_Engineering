<%@ include file="../common/IncludeTop.jsp"%>
<head>
    <link rel="StyleSheet" href="../css/styles.css" type="text/css" media="screen"/>
</head>
<div class="container">
    <div class="row">
        <stripes:form
                beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                enctype="multipart-form/data">
            <h3>AnimalInfo</h3>
            <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
                <thead>
                <tr>
                    <th colspan="2" style="background-color: #eeeeee; text-align: center;">animalMating</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Title</th>
                    <td><stripes:text class="form-control"  name="animalMating.title" maxlength="50"/></td>
                </tr>
                <tr>
                    <th>Characters</th>
                    <td>
                        <c:forEach var="characters" items="${actionBean.characters}">
                            <stripes:checkbox name="animalMating.characterList" value="${characters}" checked="checked"/>${characters}
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>CharacterDetail</th>
                    <td><stripes:text class="form-control"  name="animalMating.characters" maxlength="50"/></td>
                </tr>
                <tr>
                    <th>Contents</th>
                    <td><stripes:text class="form-control" name="animalMating.contents" maxlength="2048" style="height: 350px;"/></td>
                </tr>
                </tbody>
            </table>
            <h3>AnimalCategory</h3>
            <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
                <tr>
                    <td>Animal Category:</td>
                    <td>
                        <stripes:select name="animalMating.categoryid">
                            <stripes:options-collection collection="${actionBean.categories}"  />
                        </stripes:select>
                    </td>
                </tr>
                <tr>
                    <td>Animal Sex</td>
                    <td>
                        <stripes:select name="animalMating.sex">
                            <stripes:options-collection collection="${actionBean.sex}"  />
                        </stripes:select>
                    </td>
                </tr>
            </table >
            <h3>ChangeImg</h3>
            <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
                <tr>
                    <td>
                        <details>
                            <summary>ChangeImg</summary>
                            <stripes:label for="editFile"/>
                            <stripes:file name="fileBean" style="text-align: left" id="editFile" />
                            <stripes:submit name="uploadImg" value="edit"/>
                        </details>
                    </td>
                </tr>

            </table>
        </stripes:form>
    </div>
</div>
<%@ include file="../common/IncludeBottom.jsp"%>