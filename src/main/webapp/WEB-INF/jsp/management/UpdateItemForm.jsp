<%--

       Copyright 2010-2022 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          https://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<%@ include file="../common/IncludeTop.jsp"%>

<jsp:useBean id="catalog"
             class="org.mybatis.jpetstore.web.actions.CatalogActionBean" />

<div id="BackLink"><stripes:link
        beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"
        event="viewAllProduct">
    Return to Product
</stripes:link></div>

<div id="Catalog">

    <h2>${actionBean.product.name}</h2>
<stripes:form
        beanclass="org.mybatis.jpetstore.web.actions.AdminActionBean"
        focus="">
    <table>
        <tr>
            <th>Item ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>Quantity</th>
        </tr>
            <tr>
                <td>${actionBean.item.itemId}</td>
                <td><stripes:text name="item.attribute1" value="${actionBean.item.attribute1}" /></td>
                <td><stripes:text name="item.listPrice" value="${actionBean.item.listPrice}" /></td>
                <td><stripes:text name="item.quantity" value="${actionBean.item.quantity}" /></td>
            </tr>
    </table>
    <stripes:submit name="updateItem" value="SUBMIT" />
</stripes:form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>





