<%@ page import="ru.inurgalimov.crud.model.User" %>
<%@ page import="ru.inurgalimov.crud.logic.ValidateService" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset=\"UTF-8\">
    <title>Users</title>
</head>
<body>
<table style="border: solid black">
    <tr>
        <th>User ID</th>
        <th>User name</th>
        <th>User login</th>
        <th>User email</th>
        <th>User create date</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
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
                    <input type='submit' value='delete'>
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>