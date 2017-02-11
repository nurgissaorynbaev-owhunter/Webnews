<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
<div class="container c-header">
    <c:set var="user" value="${sessionScope.user}"/>
    <c:choose>
    <c:when test="${user.role eq 'admin'}">
        <div class="row text-right">
            <ul class="list-inline">
                <li>
                    <a class="btn btn-default btn-link" href="/pages/home" role="button">Home</a>
                </li>
                <li>
                    <form action="/pages/showAddProduct" method="get">
                        <input type="submit" class="btn btn-link" value="Add product">
                    </form>
                </li>
                <li>
                    <form action="/pages/userList" method="get">
                        <input type="submit" class="btn btn-link" value="Users">
                    </form>
                </li>
                <li>
                    <form action="/pages/orders" method="get">
                        <input type="submit" class="btn btn-link" value="Orders">
                    </form>
                </li>
                <li>
                    <form action="/pages/signOut" method="get">
                        <input type="submit" class="btn btn-link" value="Sign out">
                    </form>
                </li>
                <li>
                    <form action="/pages/showProfile" method="get">
                        <input type="submit" class="btn btn-link" value="${user.firstName}">
                    </form>
                </li>
            </ul>
        </div>
    </c:when>
    <c:otherwise>
    <div class="row text-right">
        <ul class="list-inline">
            <li>
                <a class="btn btn-default" href="/pages/home" role="button">Home</a>
            </li>
            <li>
                <form action="/pages/shoppingCart" method="get">
                    <input type="submit" class="btn btn-default" value="Shopping cart">
                </form>
            </li>
            <c:choose>
                <c:when test="${user.role eq 'user'}">
                    <c:if test="${sessionScope.productOrders ne null}">
                        <li>
                            <form action="/pages/myOrders" method="get">
                                <input type="submit" class="btn btn-link" value="My Orders">
                            </form>
                        </li>
                    </c:if>
                    <li>
                        <form action="/pages/signOut" method="get">
                            <input type="submit" class="btn btn-link" value="Sign out">
                        </form>
                    </li>
                    <li>
                        <form action="/pages/showProfile" method="get">
                            <input type="submit" class="btn btn-link" value="${user.firstName}">
                        </form>
                    </li>
                </c:when>
                <c:when test="${user.role ne 'user'}">
                    <li>
                        <a href="/pages/showSignIn">Sign in</a>
                    </li>
                    <li>
                        <a href="/pages/showSignUp">Sign up</a>
                    </li>
                </c:when>
            </c:choose>
        </ul>
    </div>
</div>
</c:otherwise>
</c:choose>
</div>
</body>
</html>
