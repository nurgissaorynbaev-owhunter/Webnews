<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container c-top10">
    <c:set var="product" value="${sessionScope.product}"/>
    <form action="/pages/addProduct" method="post" class="form-horizontal" enctype="multipart/form-data">
        <div class="col-md-offset-3 col-md-6">
            <div class="form-group text-center">
                <h2>Add Product</h2>
            </div>
            <c:if test="${sessionScope.addProductViolations != null}">
                <c:forEach var="violation" items="${sessionScope.addProductViolations}">
                    <p style="color: red">${violation.key} : ${violation.value}</p>
                </c:forEach>
            </c:if>
            <div class="form-group">
                <input type="text" class="form-control" name="title" value="${product.title}" placeholder="Title"
                       required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="author" value="${product.author}" placeholder="Author"
                       required>
            </div>
            <div class="form-group">
                <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
                <div class="input-group">
                    <div class="input-group-addon">$</div>
                    <input type="text" class="form-control" name="price" value="${product.price}"
                           id="exampleInputAmount" placeholder="Amount"
                           required>
                    <div class="input-group-addon">.00</div>
                </div>
            </div>
            <div class="form-group">
            <textarea class="form-control" rows="10" name="description" placeholder="Product Description"
                      required>${product.description}</textarea>
            </div>
            <div class="form-group">
                <textarea class="form-control" rows="7" name="details" placeholder="Product Details"
                          required>${product.details}</textarea>
            </div>
            <div class="form-group">
                <textarea class="form-control" rows="5" name="aboutAuthor"
                          placeholder="About The Author">${product.aboutAuthor}</textarea>
            </div>
            <div class="form-group">
                <label for="InputImage">Choose Image</label>
                <input type="file" name="fileUpload" id="InputImage" required>
            </div>
            <div class="form-group text-right">
                <button type="submit"class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
