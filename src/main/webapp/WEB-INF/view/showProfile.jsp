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
<form action="/pages/profile" method="post" class="form-horizontal">
    <c:set var="user" value="${sessionScope.user}"/>
    <c:set var="customer" value="${sessionScope.customer}"/>
    <div class="text-center">
        <h1>Personal info</h1>
    </div>
    <div class="col-md-offset-4 col-md-4">
        <c:if test="${sessionScope.userViolations ne null}">
            <c:forEach var="violations" items="${sessionScope.userViolations}">
                <p style="color: red">${violations.key} : ${violations.value}</p>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.customerViolations ne null}">
            <c:forEach var="violations" items="${sessionScope.customerViolations}">
                <p style="color: red">${violations.key} : ${violations.value}</p>
            </c:forEach>
        </c:if>
        <div class="form-group">
            <label for="inputFirstName">FirstName</label>
            <input type="text" class="form-control" name="fname" id="inputFirstName"
                   value="${user.firstName}">
        </div>
        <div class="form-group">
            <label for="inputLastName">LastName</label>
            <input type="text" class="form-control" name="lname" id="inputLastName"
                   value="${user.lastName}">
        </div>
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="text" class="form-control" name="email" id="inputEmail"
                   value="${user.email}">
        </div>
        <div class="form-group">
            <label for="inputCounty">Country</label>
            <input type="text" class="form-control" id="inputCounty" name="country" value="${customer.country}" required>
        </div>
        <div class="form-group">
            <label for="inputCity">City</label>
            <input type="text" class="form-control" id="inputCity" name="city" value="${customer.city}" required>
        </div>
        <div class="form-group">
            <label for="inputHomeAddress">Home address</label>
            <input type="text" class="form-control" id="inputHomeAddress" name="homeAddress"
                   value="${customer.homeAddress}" required>
        </div>
        <div class="form-group">
            <label for="inputPhoneNumber">Phone Number</label>
            <input type="text" class="form-control" id="inputPhoneNumber" name="phoneNumber"
                   value="${customer.phoneNumber}" required>
        </div>
        <div class="form-group">
            <label for="inputPassword">New password</label>
            <input type="password" class="form-control" name="pwd" id="inputPassword">
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm new password</label>
            <input type="password" class="form-control" name="confirmPwd" id="confirmPassword">
        </div>
        <div class="form-group text-right">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form>
</body>
</html>
