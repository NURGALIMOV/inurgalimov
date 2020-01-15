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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Сервлет для CRUD.
 */
public class UserServlet extends HttpServlet {

    /** Сепаратор для текущей системы */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /** Логгер. */
    private static final Logger LOGGER = LogManager.getLogger(UserServlet.class.getName());

    /** Константа для получения действия. */
    private static final String ACTION = "action";

    /** Константа для получения имени пользователя. */
    private static final String NAME = "name";

    /** Константа для получения логина пользователя. */
    private static final String LOGIN = "login";

    /** Константа для получения почты пользователя. */
    private static final String EMAIL = "email";

    /** Константа для получения ID пользователя. */
    private static final String ID = "id";

    /** Сервис валидации данных. */
    private final Validate validate = ValidateService.getInstance();

    /** Выполняемые действия сервлетом. */
    private final Map<String, Consumer<String[]>> actions = new HashMap();

    {
        actions.put("add", args -> validate.add(new User(args[0], args[1], args[2])));
        actions.put("update", args -> validate.update(new User(
                args[0],
                args[1],
                args[2],
                UUID.fromString(args[3]))
        ));
        actions.put("delete", args -> validate.delete(new User(
                args[0],
                args[1],
                args[2],
                UUID.fromString(args[3]))
        ));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        try (PrintWriter pw = new PrintWriter(resp.getOutputStream())) {
            pw.append(String.format("Users:%s", LINE_SEPARATOR));
            validate.findAll().forEach(user -> {
                pw.append(String.format("User ID - %s%s", user.getId(), LINE_SEPARATOR));
                pw.append(String.format("User name - %s%s", user.getName(), LINE_SEPARATOR));
                pw.append(String.format("User login - %s%s", user.getLogin(), LINE_SEPARATOR));
                pw.append(String.format("User email - %s%s", user.getEmail(), LINE_SEPARATOR));
                pw.append(String.format("User create date - %s%s", user.getCreateDate(), LINE_SEPARATOR));
                pw.append(LINE_SEPARATOR);
            });
            pw.flush();
        } catch (IOException io) {
            LOGGER.error("Ошибка чтения/записи!", io);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION);
        actions.getOrDefault(
                action,
                args -> {
                    throw new UnsupportedOperationException(String.format("Операция %s не доступна.", action));
                }).accept(new String[]{
                    req.getParameter(NAME),
                    req.getParameter(LOGIN),
                    req.getParameter(EMAIL),
                    req.getParameter(ID)
            });
    }
}
