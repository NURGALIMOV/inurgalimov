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

public class UserCreateServlet extends HttpServlet {

    /** Сепаратор для текущей системы */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** Логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserCreateServlet.class.getName());

    /** Сервис валидации данных. */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        StringBuilder form = new StringBuilder();
        form.append("<form action='")
                .append(req.getContextPath()).append("/create' method='post'>")
                .append("User name : <input type='text' name='name'/><br/>")
                .append("User login : <input type='text' name='login'/><br/>")
                .append("User email : <input type='text' name='email'/><br/>")
                .append("<input type='submit' value='create'></form>");

        try(PrintWriter pw = new PrintWriter(resp.getOutputStream())) {
            pw.append("<!DOCTYPE html>" +
                      "<html lang=\"en\">" +
                      "<head>" +
                      "    <meta charset=\"UTF-8\">" +
                      "    <title>Create user</title>" +
                      "</head>" +
                      "<body>" +
                      form.toString() +
                      "<br/>" +
                      "</body>" +
                      "</html>");
            pw.flush();
        } catch (IOException io) {
            LOGGER.error("Ошибка чтения/записи!", io);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate.add(new User(
                req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("email")));
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
