package ru.inurgalimov.crud.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class UserUpdateServlet extends HttpServlet {
    /** Сепаратор для текущей системы */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** Логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserUpdateServlet.class.getName());

    /** Сервис валидации данных. */
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
                .append("<input type='submit' value='edit'></form>");

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
        validate.update(new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email"),
                UUID.fromString(req.getParameter("id"))));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
