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
    <h3>Customer address</h3>
    <hr>
    <div class="row">
        <div class="col-md-6">
            <form action="/pages/customer" method="post">
                <div class="form-group">
                    <label for="inputFullName">Fullname</label>
                    <input type="text" class="form-control" id="inputFullName" name="fullName" placeholder="Type full Firstname, Lastname, Surname" required>
                </div>
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
                    <input type="text" class="form-control" id="inputHomeAddress" name="homeAddress" placeholder="Street, house, flat number.." required>
                </div>
                <div class="form-group">
                    <label for="inputPhoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="inputPhoneNumber" name="phoneNumber" placeholder="+7 700 948 82 18" required>
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input type="text" class="form-control" id="inputEmail" name="email" required>
                </div>
                <div class="form-group text-right">
                    <input type="submit" class="btn btn-primary" value="Next">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


