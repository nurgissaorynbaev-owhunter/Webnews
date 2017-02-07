<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div class="container c-wrapper">
    <table class="table">
        <div class="row">
            <tr>
                <th>Id</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </div>
        <c:forEach var="user" items="${requestScope.users}">
            <div class="row">
                <tr>
                    <c:choose>
                        <c:when test="${user.status eq 'admin'}">
                            <td>
                                <small style="color: green">${user.id}</small>
                            </td>
                            <td style="color: green">
                                <small>${user.firstName}</small>
                            </td>
                            <td style="color: green">
                                <small>${user.lastName}</small>
                            </td>
                            <td style="color: green">
                                <small>${user.email}</small>
                            </td>
                        </c:when>
                        <c:when test="${user.status eq 'ban'}">
                            <td>
                                <small style="color: red">${user.id}</small>
                            </td>
                            <td style="color: red">
                                <small>${user.firstName}</small>
                            </td>
                            <td style="color: red">
                                <small>${user.lastName}</small>
                            </td>
                            <td style="color: red">
                                <small>${user.email}</small>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <small>${user.id}</small>
                            </td>
                            <td>
                                <small>${user.firstName}</small>
                            </td>
                            <td>
                                <small>${user.lastName}</small>
                            </td>
                            <td>
                                <small>${user.email}</small>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <div>
                        <c:if test="${user.role ne 'admin'}">
                            <td>
                                <form action="/pages/userList" method="get">
                                        <input type="hidden" name="bannedUserId" value="${user.id}">
                                    <input type="submit" class="btn btn-warning btn-sm" value="Ban">
                                </form>
                            </td>
                            <td>
                                <form action="/pages/userList" method="get">
                                        <input type="hidden" name="unbannedUserId" value="${user.id}">
                                    <input type="submit" class="btn btn-success btn-sm" value="Unban">
                                </form>
                            </td>
                            <td>
                                <form action="/pages/userList" method="get">
                                    <input type="hidden" name="deletedUserId" value="${user.id}">
                                    <input type="submit" class="btn btn-danger btn-sm" value="Delete">
                                </form>
                            </td>
                        </c:if>
                    </div>
                </tr>
            </div>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
