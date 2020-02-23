package ru.inurgalimov.crud.presentation;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inurgalimov.crud.logic.Validate;
import ru.inurgalimov.crud.logic.ValidateService;
import ru.inurgalimov.crud.model.Role;
import ru.inurgalimov.crud.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class UserCreateServlet extends HttpServlet {

    /**
     * Сепаратор для текущей системы
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(UserCreateServlet.class.getName());

    /**
     * Путь для создания пользователя.
     */
    private static final String PATH_CREATE_USER = "/WEB-INF/create/Create.jsp";

    /**
     * Действия с моделью данных.
     */
    private static final Map<String, BiConsumer<String, User>> actions = new HashMap<>();

    static {
        actions.put("name", (s, u) -> u.setName(s));
        actions.put("login", (s, u) -> u.setLogin(s));
        actions.put("email", (s, u) -> u.setEmail(s));
        actions.put("role", (s, u) -> u.setRole(s.equals("user") ? Role.USER : Role.ADMINISTRATOR));
        actions.put("password", (s, u) -> u.setPassword(s));
        actions.put("file", (s, u) -> u.setPhotoId(""));
    }

    /**
     * Сервис валидации данных.
     */
    private final Validate validate = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PATH_CREATE_USER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            Path dir = Paths.get("images");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            User newUser = new User();
            for (FileItem item : upload.parseRequest(req)) {
                if (!item.isFormField() && !item.getName().isEmpty()) {
                    Files.copy(item.getInputStream(), dir.resolve(Paths.get(item.getName())),
                            StandardCopyOption.REPLACE_EXISTING);
                    newUser.setPhotoId(item.getName());
                } else {
                    actions.get(item.getFieldName()).accept(item.getString(), newUser);
                }
            }
            validate.add(newUser);
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } catch (FileUploadException e) {
            LOGGER.error("Ошибка при получении файлов.", e);
        }

    }
}
