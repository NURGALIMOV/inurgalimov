<%@ page import="ru.inurgalimov.crud.model.User" %>
<%@ page import="ru.inurgalimov.crud.logic.ValidateService" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Пользователи</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>User ID</th>
            <th>User name</th>
            <th>User login</th>
            <th>User email</th>
            <th>User create date</th>
            <th>edit</th>
            <th>delete</th>
            <th>Download</th>
            <th>Photo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"/>
                        <input type="submit" value="edit">
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/list" method="post">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>"/>
                        <input type="hidden" name="photoId" value="<c:out value="${user.photoId}"></c:out>"/>
                        <input type='submit' value='delete'>
                    </form>
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/download?name=<c:out value="${user.photoId}"></c:out>">Download</a>
                </td>
                <td>
                    <img src="${pageContext.servletContext.contextPath}/download?name=<c:out value="${user.photoId}"></c:out>"
                         width="50px"
                         height="50px"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <input type='submit' value='Создать пользователя'>
    </form>
    <form action="${pageContext.servletContext.contextPath}/signout" method="get">
        <input type='submit' value='Выход'>
    </form>
</div>
</body>
</html>