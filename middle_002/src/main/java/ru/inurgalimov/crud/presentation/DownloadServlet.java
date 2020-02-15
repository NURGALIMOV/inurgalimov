package ru.inurgalimov.crud.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Сервлет для закачки.
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        Files.copy(Paths.get("images", name), resp.getOutputStream());
    }
}
