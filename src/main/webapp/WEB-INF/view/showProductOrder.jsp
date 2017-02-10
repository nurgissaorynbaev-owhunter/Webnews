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
<div class="container">
    <table class="table">
        <div class="row">
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </div>
        <c:set var="productQuantityMap" value="${sessionScope.productQuantityMap}"/>
        <c:forEach var="product" items="${sessionScope.products}">
            <div class="row">
                <tr>
                    <td>
                        <small>${product.title}</small>
                    </td>
                    <td>
                        <small>$${product.price}</small>
                    </td>
                    <td>
                        <small>${productQuantityMap[product.id]}</small>
                    </td>
                </tr>
            </div>
        </c:forEach>
    </table>
    <hr>
    <h3 class="text-right">Total cost: $<c:out value="${sessionScope.totalCost}"/></h3><br>
    <form action="/pages/productOrder" method="get">
        <input type="submit" class="btn btn-primary text-right" name="checkoutClicked" value="Checkout">
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
