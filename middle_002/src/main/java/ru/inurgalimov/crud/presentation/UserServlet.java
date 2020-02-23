package ru.inurgalimov.crud.presentation;

import ru.inurgalimov.crud.utils.Utils;
import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.model.Role;
import ru.inurgalimov.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Сервлет для CRUD.
 */
public class UserServlet extends HttpServlet {

    /**
     * Путь до jsp для отображения текущего пользователя.
     */
    private static final String PATH_FOR_CURRENT_USER = "/WEB-INF/CurrentUser.jsp";

    /**
     * Сервис валидации данных.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute(Utils.KEY_FOR_GET_LOGIN);
        String password = (String) session.getAttribute(Utils.KEY_FOR_GET_PASSWORD);

        User user = validate.findAll().stream().filter(u -> login.equals(u.getLogin()) && password.equals(u.getPassword()))
                .findFirst().orElse(new User());
        Role userRole = user.getRole();
        session.setAttribute(Utils.KEY_FOR_GET_ROLE, userRole);
        if (Objects.nonNull(userRole) && userRole.equals(Role.ADMINISTRATOR)) {
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } else {
            req.setAttribute(Utils.KEY_FOR_GET_USER, user);
            req.getRequestDispatcher(PATH_FOR_CURRENT_USER).forward(req, resp);
        }
    }
}
