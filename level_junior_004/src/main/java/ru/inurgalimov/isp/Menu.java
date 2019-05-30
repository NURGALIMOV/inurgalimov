package ru.inurgalimov.isp;

import java.util.*;

/**
 * Класс описывающий меню.
 *
 * @author Ilshat Nurgalimov
 * @since 30.05.2019
 */
public class Menu implements Select {
    /**
     * Имя меню.
     */
    private String name;
    /**
     * Список подменю.
     */
    private Map<String, Menu> menuMap;
    /**
     * Системный перевод строки.
     */
    private static final String LN = System.getProperty("line.separator");

    /**
     * Конструктор.
     *
     * @param name - имя меню.
     */
    public Menu(String name) {
        this.name = name;
        menuMap = new LinkedHashMap<>();
    }

    /**
     * Добавление подменю.
     *
     * @param menu - подменю.
     */
    public void addMenu(Menu menu) {
        menuMap.put(menu.getName(), menu);
    }

    /**
     * Вывод меню.
     *
     * @param str - символ добавляется если имеются подменю.
     * @return - строковое представление меню.
     */
    public String print(String str) {
        StringBuilder show = new StringBuilder();
        show.append(String.format("%s%s%s", str, name, LN));
        if (menuMap.size() > 0) {
            for (Menu m : menuMap.values()) {
                show.append(String.format("-%s", m.print(str + "-")));
            }
        }
        return show.toString();
    }

    /**
     * Выбор пункта меню.
     *
     * @param str - разделитель.
     * @param select - выбранный пункт меню.
     * @return - возвращаем выбранный пункт меню.
     */
    @Override
    public Menu select(String str, String select) {
        Menu result = null;
        if (select.equals(name)) {
            result = this;
        } else {
            String[] s = select.split("\\W");
            if (s[0].equals(name)) {
                String temp = s[0];
                result = this;
                Map<String, Menu> map = menuMap;
                for (int i = 1; i < s.length; i++) {
                    temp += str + s[i];
                    result = map.get(temp);
                    if (select.equals(result.getName())) {
                        break;
                    }
                    map = result.getMenuMap();
                }
            }
        }

        return result;
    }

    /**
     * Getter для поля name.
     *
     * @return - возвращаем имя меню.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter для поля menuMap.
     *
     * @return
     */
    public Map<String, Menu> getMenuMap() {
        return menuMap;
    }
}
