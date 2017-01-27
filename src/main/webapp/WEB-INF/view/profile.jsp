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
<form class="form-horizontal">
    <div class="col-md-offset-4 col-md-4 c-top70">
        <div class="form-group text-center">
            <h3>My Profile</h3>
        </div>
        <div class="form-group">
            <label for="inputFirstName">FirstName</label>
            <input type="text" class="form-control" id="inputFirstName">
        </div>
        <div class="form-group">
            <label for="inputLastName">LastName</label>
            <input type="text" class="form-control" id="inputLastName">
        </div>
        <div class="form-group">
            <label for="inputEmail">Email</label>
            <input type="text" class="form-control" id="inputEmail">
        </div>
        <div class="form-group">
            <label for="inputPassword">New password</label>
            <input type="password" class="form-control" id="inputPassword">
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm new password</label>
            <input type="password" class="form-control" id="confirmPassword">
        </div>
        <div class="form-group text-right">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form>
</body>
</html>
