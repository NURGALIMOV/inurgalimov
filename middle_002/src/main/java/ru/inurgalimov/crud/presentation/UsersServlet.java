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

public class UsersServlet extends HttpServlet {

    /** Сепаратор для текущей системы */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** Логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UsersServlet.class.getName());

    /** Сервис валидации данных. */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        StringBuilder table =
                new StringBuilder("<table style='border: 1p solid black;' cellpadding='1' cellspacing='1' border='1'>");
        table.append("<tr>"
                + "<th>User ID</th>"
                + "<th>User name</th>"
                + "<th>User login</th>"
                + "<th>User email</th>"
                + "<th>User create date</th>"
                + "<th>edit</th>"
                + "<th>delete</th>"
                + "</tr>");
        validate.findAll().forEach(user -> {
            table.append("<tr>");
            table.append("<td>" + user.getId() + "</td>");
            table.append("<td>" + user.getName() + "</td>");
            table.append("<td>" + user.getLogin() + "</td>");
            table.append("<td>" + user.getEmail() + "</td>");
            table.append("<td>" + user.getCreateDate() + "</td>");
            table.append(String.format("<td>"
                    + "<form action='%s/edit' method='get'>"
                    + "<input type='hidden' name='id' value='%s'/>"
                    + "<input type='submit' value='edit'></form>"
                    + "</td>", req.getContextPath(), user.getId()));
            table.append(String.format("<td>"
                    + "<form action='%s/list' method='post'>"
                    + "<input type='hidden' name='id' value='%s'/>"
                    + "<input type='submit' value='delete'></form>"
                    + "</td>", req.getContextPath(), user.getId()));
            table.append("</tr>");
        });
        table.append("</table>");

        try (PrintWriter pw = new PrintWriter(resp.getOutputStream())) {
            pw.append("<!DOCTYPE html>"
                    + "<html lang=\"en\">"
                    + "<head>"
                    + "    <meta charset=\"UTF-8\">"
                    + "    <title>Users</title>"
                    + "</head>"
                    + "<body>"
                    + table.toString()
                    + "</body>"
                    + "</html>");
            pw.flush();
        } catch (IOException io) {
            LOGGER.error("Ошибка чтения/записи!", io);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate.delete(
                new User(null, null, null, UUID.fromString(req.getParameter("id"))));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
