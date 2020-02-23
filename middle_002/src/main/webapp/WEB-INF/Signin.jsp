<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method='post'>
    login : <input type='text' name='login'/><br/>
    password : <input type='text' name='password'/><br/>
    <input type='submit' value='Вход'>
</form>
</body>
</html>
