<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
<c:set var="throwable" value="${requestScope['javax.servlet.error.exception']}"/>
<c:set var="statusCode" value="${requestScope['javax.servlet.error.status_code']}"/>
<c:set var="servletName" value="${requestScope['javax.servlet.error.servlet_name']}"/>
<c:set var="requestUri" value="${requestScope['javax.servlet.error.request_uri']}"/>
<div class="container c-top60">
    <div class="text-center">
        <c:if test="${servletName eq null}">
            <c:set var="servletName" value="Unknown"/>
        </c:if>
        <c:if test="${requestUri eq null}">
            <c:set var="requestUri" value="Unknown"/>
        </c:if>
        <c:choose>
            <c:when test="${statusCode ne 500}">
                <h3>Error Details</h3>
                <ul class="list-unstyled">
                    <li>
                        <strong>Status Code: ${statusCode}</strong>
                    </li>
                    <li>
                        <strong>Request URI: ${requestUri}</strong>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <h3>Exception Details</h3>
                <ul class="list-unstyled">
                    <li>Servlet Name: ${servletName}</li>
                    <li>Request URI: ${requestUri}</li>
                    <li>Exception message: ${throwable.message}</li>
                </ul>
            </c:otherwise>
        </c:choose>
        <div class="c-top60">
            <form action="/pages/home" method="get">
                <input type="submit" class="btn btn-primary" value="Home">
            </form>
        </div>
    </div>
</div>
</body>
</html>


