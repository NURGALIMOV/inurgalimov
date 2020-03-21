<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create user</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            if ($('#name').val() == '') {
                alert('Поле \'Имя\' не заполнено.');
                return false;
            }
            if ($('#login').val() == '') {
                alert('Поле \'Логин\' не заполнено.');
                return false;
            }
            if ($('#email').val() == '') {
                alert('Поле \'Почта\' не заполнено.');
                return false;
            }
            if ($('#password').val() == '') {
                alert('Поле \'Пароль\' не заполнено.');
                return false;
            }
            if ($('#id').val() == '') {
                alert('Поле \'ID\' не заполнено.');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Редактирование данных пользователя</h2>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit" method='post'
          onsubmit="return validate()">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">User name :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Введите имя" name="name"
                       value="${user.name}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">User login :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" placeholder="Введите логин" name="login"
                       value="${user.login}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">User email :</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" placeholder="Введите email" name="email"
                       value="${user.email}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="id">User id :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id" placeholder="Введите логин" name="id"
                       value="${user.id}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">User password :</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password"
                       value="${user.password}">
            </div>
        </div>
        <c:if test="${role}">
            <div class="dropdown">
                <select name='role'>
                    <option value="admin">admin</option>
                    <option value="user">user</option>
                </select>
            </div>
        </c:if>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Редактировать</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
