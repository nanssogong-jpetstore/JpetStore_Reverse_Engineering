<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <link rel="StyleSheet" href="../css/jpetstore.css" type="text/css" media="screen" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link rel="StyleSheet" href="../css/styles.css" type="text/css" media="screen"/>

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore Demo</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />

</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent"><stripes:link
                beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean">
            <img src="../images/logo-topbar.gif" />
        </stripes:link></div>
    </div>

    <div id="Menu">
        <div id="MenuContent"><stripes:link
                beanclass="org.mybatis.jpetstore.web.actions.CartActionBean"
                event="viewCart">
            <img align="middle" name="img_cart" src="../images/cart.gif" />
        </stripes:link> <img align="middle" src="../images/separator.gif" />
            <c:if
                    test="${sessionScope.accountBean == null}">
                <stripes:link
                        beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"
                        event="signonForm">
                    Sign In
                </stripes:link>
            </c:if> <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${!sessionScope.accountBean.authenticated}">
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"
                            event="signonForm">
                        Sign In
                    </stripes:link>
                </c:if>
            </c:if> <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${sessionScope.accountBean.authenticated}">
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"
                            event="signoff">
                        Sign Out
                    </stripes:link>
                    <img align="middle" src="../images/separator.gif" />
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"
                            event="editAccountForm">
                        My Account
                    </stripes:link>
                </c:if>
            </c:if>
            <img align="middle" src="../images/separator.gif" /> <a
                    href="../help.html">?</a>
            <img align="middle" src="../images/separator.gif" />
            <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${sessionScope.accountBean.authenticated}">
                    <c:if test="${sessionScope.accountBean.account.rank eq 'MANAGEMENT'}" >
                        <stripes:link
                                beanclass="org.mybatis.jpetstore.web.actions.AdminActionBean"
                                event="viewAllProduct">
                            Admin Dashboard
                        </stripes:link>
                    </c:if>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.accountBean != null}">
                <c:if test="${sessionScope.accountBean.authenticated}">
                    <img align="middle" src="../images/separator.gif" />
                    <stripes:link
                            beanclass="org.mybatis.jpetstore.web.actions.AnimalActionBean"
                            event="listAnimalAccount">
                        AnimalMating
                    </stripes:link>
                </c:if>
            </c:if>

        </div>
    </div>

    <div id="Search">
    </div>

</div>

<div id="Content"><stripes:messages /></div>