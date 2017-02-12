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
<div class="container">
    <h3 class="text-center">Customer address</h3>
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <c:if test="${sessionScope.customerRegistrationViolations ne null}">
                <c:forEach var="violations" items="${sessionScope.customerRegistrationViolations}">
                    <p style="color: red">${violations.key} : ${violations.value}</p>
                </c:forEach>
            </c:if>
            <form action="/pages/customerRegistration" method="post">
                <c:set var="user" value="${sessionScope.user}"/>
                <c:choose>
                    <c:when test="${user ne null}">
                        <input type="hidden" name="userFirstName" value="${user.firstName}">
                        <input type="hidden" name="userLastName" value="${user.lastName}">
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="inputFullName">Fullname</label>
                            <input type="text" class="form-control" id="inputFullName" name="fullName"
                                   placeholder="Firstname, Lastname, Surname" required>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-group">
                    <label for="inputCounty">Country</label>
                    <input type="text" class="form-control" id="inputCounty" name="country" required>
                </div>
                <div class="form-group">
                    <label for="inputCity">City</label>
                    <input type="text" class="form-control" id="inputCity" name="city" required>
                </div>
                <div class="form-group">
                    <label for="inputHomeAddress">Home address</label>
                    <input type="text" class="form-control" id="inputHomeAddress" name="homeAddress"
                           placeholder="Street, house, flat number.." required>
                </div>
                <div class="form-group">
                    <label for="inputPhoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="inputPhoneNumber" name="phoneNumber"
                           placeholder="+7 700 948 82 18" required>
                </div>
                <c:choose>
                    <c:when test="${user ne null}">
                        <input type="hidden" name="userEmail" value="${user.email}">
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="inputEmail">Email</label>
                            <input type="text" class="form-control" id="inputEmail" name="email" required>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="form-group text-right">
                    <input type="submit" class="btn btn-primary" value="Submit">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


