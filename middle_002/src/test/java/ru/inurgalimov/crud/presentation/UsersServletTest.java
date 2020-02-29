package ru.inurgalimov.crud.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.logic.ValidateServiceStub;
import ru.inurgalimov.crud.model.Role;
import ru.inurgalimov.crud.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Тест для класса UsersServlet.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UsersServletTest {

    /**
     * Тест удаления пользователя.
     *
     * @throws ServletException в случае ошибки работы сервлета.
     * @throws IOException в случае ошибки работы сервлета.
     */
    @Test
    public void whenDeleteUser() throws ServletException, IOException {
        Validate stub = ValidateServiceStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(stub);
        User testUser = new User();
        stub.add(testUser);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(testUser.getId().toString());
        new UsersServlet().doPost(req, resp);
        assertTrue(stub.findAll().isEmpty());
    }

    /**
     * Тест получения пользователей.
     *
     * @throws ServletException в случае ошибки работы сервлета.
     * @throws IOException в случае ошибки работы сервлета.
     */
    @Test
    public void whenGetUsers() throws ServletException, IOException {
        Validate stub = ValidateServiceStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(stub);
        stub.add(new User());
        stub.add(new User());
        stub.add(new User());
        stub.add(new User());
        stub.add(new User());

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(session.getAttribute("role")).thenReturn(Role.ADMINISTRATOR);
        when(req.getSession()).thenReturn(session);
        when(req.getRequestDispatcher("/WEB-INF/list/AllUsers.jsp")).thenReturn(dispatcher);
        new UsersServlet().doGet(req, resp);
        verify(req).setAttribute("users", stub.findAll());
        verify(req, Mockito.times(1)).setAttribute("users", stub.findAll());
    }
}