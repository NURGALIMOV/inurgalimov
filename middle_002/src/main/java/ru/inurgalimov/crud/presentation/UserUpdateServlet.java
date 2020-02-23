package ru.inurgalimov.crud.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.model.Role;
import ru.inurgalimov.crud.model.User;
import ru.inurgalimov.crud.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.UUID;

public class UserUpdateServlet extends HttpServlet {
    /**
     * Сепаратор для текущей системы
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(UserUpdateServlet.class.getName());

    /**
     * Сервис валидации данных.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = validate.findById(
                new User(null, null, null, UUID.fromString(req.getParameter("id"))));
        resp.setContentType("text/html; charset=utf-8");
        StringBuilder form = new StringBuilder();
        form.append("<form action='")
                .append(req.getContextPath()).append("/edit' method='post'>")
                .append("User name : <input type='text' name='name' value='")
                .append(user.getName()).append("'/><br/>")
                .append("User login : <input type='text' name='login' value='")
                .append(user.getLogin()).append("'/><br/>")
                .append("User email : <input type='text' name='email' value='")
                .append(user.getEmail()).append("'/><br/>")
                .append("User id : <input type='text' name='id' value='")
                .append(user.getId()).append("'/><br/>")
                .append("User password : <input type='text' name='password' value='")
                .append(user.getPassword()).append("'/><br/>");
        if (((Role) req.getSession().getAttribute(Utils.KEY_FOR_GET_ROLE)).equals(Role.ADMINISTRATOR)) {
            form.append("<select name='role'>")
                    .append("<option value='admin'>admin</option>")
                    .append("<option value = 'user' > user </option >")
                    .append("</select >");
        }
        form.append("<input type='submit' value='edit'></form>");

        try (PrintWriter pw = new PrintWriter(resp.getOutputStream())) {
            pw.append("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "    <meta charset=\"UTF-8\">"
                    + "    <title>Create user</title>"
                    + "</head>"
                    + "<body>"
                    + form.toString()
                    + "<br/>"
                    + "</body>"
                    + "</html>");
            pw.flush();
        } catch (IOException io) {
            LOGGER.error("Ошибка чтения/записи!", io);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                UUID.fromString(req.getParameter("id")));
        user.setPassword(req.getParameter("password"));
        String role = req.getParameter("role");
        if (Objects.nonNull(role) && !role.isEmpty()) {
            user.setRole(role.equals("admin") ? Role.ADMINISTRATOR : Role.USER);
        } else {
            user.setRole(Role.USER);
        }
        validate.update(user);
        if (((Role) req.getSession().getAttribute(Utils.KEY_FOR_GET_ROLE)).equals(Role.ADMINISTRATOR)) {
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        }
    }
}
