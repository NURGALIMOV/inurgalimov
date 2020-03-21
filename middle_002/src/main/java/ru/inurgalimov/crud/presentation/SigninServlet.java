package ru.inurgalimov.crud.presentation;

import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сервлет для входа в систему.
 */
public class SigninServlet extends HttpServlet {

    /**
     * Путь до jsp.
     */
    private static final String PATH_CREATE_USER = "/WEB-INF/Signin.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PATH_CREATE_USER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(Utils.KEY_FOR_GET_LOGIN);
        String password = req.getParameter(Utils.KEY_FOR_GET_PASSWORD);
        if (ValidateService.getInstance().isCredentional(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute(Utils.KEY_FOR_GET_LOGIN, login);
            session.setAttribute(Utils.KEY_FOR_GET_PASSWORD, password);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute(Utils.KEY_FOR_GET_ERROR, "Нет доступа.");
            doGet(req, resp);
        }

    }
}
