<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=\"UTF-8\">
    <title>Create user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method='post' enctype="multipart/form-data">
    <select name='role'>
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select>
    User name : <input type='text' name='name'/><br/>
    User login : <input type='text' name='login'/><br/>
    User email : <input type='text' name='email'/><br/>
    User password : <input type='text' name='password'/><br/>
    <div class="checkbox">
        <input type="file" name="file">
    </div>
    <input type='submit' value='Создать'>
</form>
</body>
</html>
