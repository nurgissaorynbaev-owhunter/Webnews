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
<form action="/pages/signIn" method="post" class="form-horizontal">
    <div class="col-md-offset-5 col-md-2 c-top50">
        <div class="form-group">
            <h3>Please sign in</h3>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="email" placeholder="Email address" required>
            <input type="password" class="form-control" name="pwd" placeholder="Password" required>
        </div>
        <div class="form-group">
            <label>
                <input type="checkbox"> Check me out
            </label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </div>
        <div class="form-group text-right">
            <a href="http://localhost:8080/pages/showSignUp">Sign up</a>
        </div>
    </div>
</form>
</body>
</html>
