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
import javax.servlet.http.HttpSession;
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
     * Путь для открытия страницы редактирования данных пользователя.
     */
    private static final String PATH_FOR_EDIT_USER = "/WEB-INF/update/Update.jsp";

    /**
     * Сервис валидации данных.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = validate.findById(
                new User(null, null, null, UUID.fromString(req.getParameter("id"))));
        req.setAttribute(Utils.KEY_FOR_GET_USER, user);
        req.setAttribute(Utils.KEY_FOR_GET_ROLE,
                ((Role) req.getSession().getAttribute(Utils.KEY_FOR_GET_ROLE)).equals(Role.ADMINISTRATOR));
        req.getRequestDispatcher(PATH_FOR_EDIT_USER).forward(req, resp);
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
