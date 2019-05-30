package ru.inurgalimov.isp;

/**
 * Интерфейс для выбора пункта меню.
 *
 * @author Ilshat Nurgalimov
 * @since 03.06.2019
 */
public interface Select {
    /**
     * Выбор пункта меню.
     *
     * @param str - разделитель.
     * @param select - выбранный пункт меню.
     * @return - возвращаем выбранный пункт меню.
     */
    Menu select(String str, String select);
}
