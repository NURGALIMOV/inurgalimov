<%@ page import="ru.inurgalimov.crud.model.User" %>
<%@ page import="ru.inurgalimov.crud.logic.ValidateService" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset=\"UTF-8\">
    <title>Users</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    if (id != null) {
        ValidateService.getInstance().delete(
                new User(null, null, null, UUID.fromString(id)));
    }
%>
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
    <% for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="edit">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/list/" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type='submit' value='delete'>
            </form>
        </td>
    </tr>
    <% } %>
    </table>
</body>
</html>