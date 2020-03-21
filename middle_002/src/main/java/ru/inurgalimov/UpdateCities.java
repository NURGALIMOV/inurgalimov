package ru.inurgalimov;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет для обновления городов.
 */
public class UpdateCities extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("country");
        JSONArray cities = new JSONArray();
        if ("Russian".equals(name)) {
            JSONObject msk = new JSONObject();
            msk.put("city0", "Москва");
            JSONObject spb = new JSONObject();
            spb.put("city1", "Санкт-Петербург");
            JSONObject kzn = new JSONObject();
            kzn.put("city2", "Казань");
            cities.put(List.of(msk, spb, kzn));
        } else {
            JSONObject ny = new JSONObject();
            ny.put("city0", "Нью-Йорк");
            JSONObject la = new JSONObject();
            la.put("city1", "Лос-Анджелес");
            JSONObject bh = new JSONObject();
            bh.put("city2", "Беверли-Хиллз");
            cities.put(List.of(ny, la, bh));
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(cities);
        writer.flush();
    }
}

