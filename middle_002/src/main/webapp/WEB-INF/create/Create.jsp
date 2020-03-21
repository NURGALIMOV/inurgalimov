<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            return true;
        }

        function update() {
            var label = document.getElementById("city");
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8082/items/update',
                data: 'country=' + $('#country').val(),
                dataType: 'json'
            }).done(function (data) {
                label.innerHTML = '';
                for (var i = 0; i < data[0].length; i++) {
                    let key = "city" + i;
                    let newOption = new Option(data[0][i][key], key);
                    label.append(newOption);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Новый пользователь</h2>
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/create"
          method='post' enctype="multipart/form-data" onsubmit="return validate()">
        <div class="dropdown">
            <select name='role'>
                <option value="admin">admin</option>
                <option value="user">user</option>
            </select>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">User name :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Введите имя" name="name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">User login :</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" placeholder="Введите логин" name="login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">User email :</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" placeholder="Введите email" name="email">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="password">User password :</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <input type="file" name="file">
                </div>
            </div>
        </div>
        <div class="dropdown">
            <select name='country' id="country" onchange="update()">
                <option value=""></option>
                <option value="Russian">Russian</option>
                <option value="USA">USA</option>
            </select>
        </div>
        <div class="dropdown">
            <select name='city' id="city"></select>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Создать</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
