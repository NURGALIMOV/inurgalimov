package ru.inurgalimov.crud.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.inurgalimov.crud.logic.ValidateServiceStub;
import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.model.Role;
import ru.inurgalimov.crud.model.User;
import ru.inurgalimov.crud.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест для класса UserUpdateServlet.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateServletTest {

    /**
     * Тест редактирования пользователя.
     *
     * @throws ServletException в случае ошибки работы сервлета.
     * @throws IOException в случае ошибки работы сервлета.
     */
    @Test
    public void whenEditUser() throws ServletException, IOException {
        Validate stub = ValidateServiceStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(stub);
        User testUser = new User();
        stub.add(testUser);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(req.getParameter("name")).thenReturn("test");
        when(req.getParameter("id")).thenReturn(testUser.getId().toString());
        when(session.getAttribute("role")).thenReturn(Role.USER);
        when(req.getSession()).thenReturn(session);
        new UserUpdateServlet().doPost(req, resp);
        assertThat(stub.findAll().iterator().next().getName(), is("test"));
    }
}