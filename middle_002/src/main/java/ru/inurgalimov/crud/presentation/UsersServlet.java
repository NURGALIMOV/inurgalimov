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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

public class UsersServlet extends HttpServlet {

    /**
     * Сепаратор для текущей системы
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Путь для представления всех пользователей.
     */
    private static final String PATH_FOR_SHOW_ALL_USERS = "/WEB-INF/list/AllUsers.jsp";

    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(UsersServlet.class.getName());

    /**
     * Сервис валидации данных.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Role userRole = (Role) session.getAttribute(Utils.KEY_FOR_GET_ROLE);
        if (Objects.nonNull(userRole) && userRole.equals(Role.ADMINISTRATOR)) {
            req.setAttribute(Utils.KEY_FOR_GET_USERS, ValidateService.getInstance().findAll());
            req.getRequestDispatcher(PATH_FOR_SHOW_ALL_USERS).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        validate.delete(
                new User(null, null, null, UUID.fromString(req.getParameter("id"))));
        String photoId = req.getParameter("photoId");
        if(Objects.nonNull(photoId) && !photoId.isEmpty()) {
            Files.deleteIfExists(Paths.get("images", req.getParameter("photoId")));
        }
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
