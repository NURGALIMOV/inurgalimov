<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
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
    </tr>
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
            <a href="${pageContext.servletContext.contextPath}/download?name=<c:out value="${user.photoId}"></c:out>">Download</a>
        </td>
        <td>
            <img src="${pageContext.servletContext.contextPath}/download?name=<c:out value="${user.photoId}"></c:out>"
                 width="50px"
                 height="50px"/>
        </td>
    </tr>
</table>
<form action="${pageContext.servletContext.contextPath}/signout" method="get">
    <input type='submit' value='Выход'>
</form>
</body>
</html>
