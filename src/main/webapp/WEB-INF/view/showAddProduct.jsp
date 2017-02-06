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
<form action="/pages/addProduct" method="post" class="form-horizontal" enctype="multipart/form-data">
    <div class="col-lg-offset-3 col-md-6 c-top20">
        <div class="form-group text-center">
            <h2>Add Product</h2>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="title" placeholder="Title" required>
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="author" placeholder="Author" required>
        </div>
        <div class="form-group">
            <label class="sr-only" for="exampleInputAmount">Amount (in dollars)</label>
            <div class="input-group">
                <div class="input-group-addon">$</div>
                <input type="text" class="form-control" name="price" id="exampleInputAmount" placeholder="Amount"
                       required>
                <div class="input-group-addon">.00</div>
            </div>
        </div>
        <div class="form-group">
            <textarea class="form-control" rows="10" name="description" placeholder="Product Description"
                      required></textarea>
        </div>
        <div class="form-group">
            <textarea class="form-control" rows="7" name="details" placeholder="Product Details" required></textarea>
        </div>
        <div class="form-group">
            <textarea class="form-control" rows="5" name="aboutAuthor" placeholder="About The Author"></textarea>
        </div>
        <div class="form-group">
            <label for="InputImage">Choose Image</label>
            <input type="file" name="fileUpload" id="InputImage" required>
        </div>
        <div class="form-group text-right">
            <button type="submit" class="btn btn-primary">Save</button>
        </div>
    </div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
