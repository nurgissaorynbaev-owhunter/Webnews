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
<jsp:include page="header.jsp"/>
<div class="container c-wrapper">
    <c:choose>
        <c:when test="${sessionScope.user.role eq 'admin'}">
            <c:forEach var="product" items="${requestScope.products}">
                <hr>
                <div class="row">
                    <div class="col-md-offset-1 col-md-3">
                        <img src="../../image/${product.image}" alt="Product image"
                             class="img-rounded c-image-percent50">
                    </div>
                    <div class="col-md-6">
                        <form action="/pages/productDetails" method="get">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" class="btn btn-default btn-link" value="${product.title}">
                        </form>
                        by <em><c:out value="${product.author}"/></em><br><br>
                        Price: $<c:out value="${product.price}"/><br><br>
                        <ul class="list-inline">
                            <li>
                                <form action="/pages/editProduct" method="get">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="submit" class="btn btn-primary btn-sm" value="Edit">
                                </form>
                            </li>
                            <li>
                                <form action="/pages/deleteProduct">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="submit" class="btn btn-danger btn-sm" value="Delete">
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:forEach var="product" items="${requestScope.products}">
                <hr>
                <div class="row">
                    <div class="col-md-offset-1 col-md-3">
                        <img src="../../image/${product.image}" alt="Product image"
                             class="img-rounded c-image-percent50">
                    </div>
                    <div class="col-md-6">
                        <form action="/pages/productDetails" method="get">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" class="btn btn-default btn-link" value="${product.title}">
                        </form>
                        by <em><c:out value="${product.author}"/></em><br><br>
                        Price: $<c:out value="${product.price}"/><br><br>
                        <form action="/pages/addToShoppingCart" method="get">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="submit" class="btn btn-info btn-sm" value="Add to Shopping Cart">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
