<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <form action="/pages/register" method="post" class="form-horizontal">
            <div class="col-md-offset-5 col-md-2">
                <div class="form-group text-left">
                    <h3>Please sign up</h3>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="fname" placeholder="Firstname" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="lname" placeholder="Lastname" required>
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" placeholder="Email address" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="pwd" placeholder="Password" required>
                </div>
                <div class="form-group text-right">
                    <button type="submit" class="form-control btn btn-primary">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
