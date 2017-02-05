<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
<%--<div class="container c-top70">--%>
<%--<ul class="list-inline">--%>
<%--<li>--%>
<%--<form action="/pages/shoppingCart" method="get">--%>
<%--<input type="submit" value="Show shopping cart">--%>
<%--</form>--%>
<%--</li>--%>
<%--<li>--%>
<%--<form action="/pages/showSignIn" method="get">--%>
<%--<input type="submit" name="submit" value="Sign In">--%>
<%--</form>--%>
<%--</li>--%>
<%--<li>--%>
<%--<form action="/pages/showProfile" method="get">--%>
<%--<input type="submit" value="Profile">--%>
<%--</form>--%>
<%--</li>--%>
<%--<li>--%>
<%--<form action="/pages/showAddProduct" method="get">--%>
<%--<input type="submit" value="Add product">--%>
<%--</form>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<jsp:include page="header.jsp"/>
<div class="container c-wrapper">
    <c:forEach var="product" items="${requestScope.products}">
        <hr>
        <div class="row">
            <div class="col-md-offset-1 col-md-3">
                <img src="../../image/${product.image}" alt="Product image" class="img-rounded c-image-percent50">
            </div>
            <div class="col-md-6">
                <c:out value="${product.title}"/><br>
                by <em><c:out value="${product.author}"/></em><br><br>
                Price: $<c:out value="${product.price}"/><br><br>
                <ul class="list-inline">
                    <li>
                        <form action="/pages/productDetails" method="get">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" class="btn btn-default btn-sm" value="Details">
                        </form>
                    </li>
                    <li>
                        <form action="/pages/addShoppingCart" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" class="btn btn-info btn-sm" value="Add to Shopping Cart">
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </c:forEach>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
