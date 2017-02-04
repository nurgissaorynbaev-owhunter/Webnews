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
<div class="container c-top10">
    <div class="row">
        <div class="col-lg-offset-8">
            <ul class="list-inline">
                <li>
                    <a class="btn btn-default" href="http://localhost:8080/pages/home" role="button">Home</a>
                </li>
                <li>
                    <form action="/pages/shoppingCart" method="get">
                        <input type="submit" class="btn btn-default" value="Shopping cart">
                    </form>
                </li>
            </ul>
        </div>
    </div>
    <hr>
</div>
</body>
</html>
