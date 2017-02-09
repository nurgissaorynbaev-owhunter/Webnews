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
    <table class="table table-striped">
        <div class="row">
            <tr>
                <th>Country</th>
                <th>City</th>
                <th>Home address</th>
                <th>Email</th>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </div>
        <c:set var="productMap" value="${requestScope.productMap}"/>
        <c:set var="customerMap" value="${requestScope.customerMap}"/>
        <c:forEach var="productOrder" items="${requestScope.productOrders}">
            <c:set var="customer" value="${customerMap[productOrder.id]}"/>
            <c:set var="product" value="${productMap[productOrder.id]}"/>
            <div class="row">
                <tr>
                    <td>
                        <small>${customer.country}</small>
                    </td>
                    <td>
                        <small>${customer.city}</small>
                    </td>
                    <td>
                            <%--<div class="c-truncate">--%>
                        <small>${customer.homeAddress}</small>
                            <%--</div>--%>
                    </td>
                    <td>
                        <small>${customer.email}</small>
                    </td>
                    <td>
                            <%--<div class="c-truncate">--%>
                        <small>${product.title}</small>
                            <%--</div>--%>
                    </td>
                    <td>
                        <small>${product.price}</small>
                    </td>
                    <td>
                        <small>${productOrder.productQuantity}</small>
                    </td>
                </tr>
            </div>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
