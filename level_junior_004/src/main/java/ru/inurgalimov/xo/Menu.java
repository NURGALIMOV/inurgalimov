package ru.inurgalimov.xo;

import java.util.*;

/**
 * Меню игры.
 *
 * @author Ilshat Nurgalimov
 * @since 05.06.2019
 */
public class Menu {

    /**
     * ResourceBundle для чтения ключевых слов.
     */
    private final ResourceBundle bundle;

    /**
     * Для ввода из консоли.
     */
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Начальные данные для игры.
     */
    private Map<String, String> config = new HashMap<>();

    /**
     * Конструктор.
     *
     * @param bundle - ResourceBundle для чтения ключевых слов.
     */
    public Menu(final ResourceBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Вывод меню.
     */
    public void show() {
        System.out.println("Добро пожаловать на игру! Для начала необходимо выбрать параметры игры.");
        System.out.println(
                "Выберите размерность игрового поля, по умолчанию размер 3x3. "
                        + "Если размер по умолчанию устраивает, введите 'y':"
        );
        config.put(bundle.getString("size"), SC.nextLine());

        System.out.println(
                "Введите количество побед, который будет результирующим при выявлении победителя. "
                        + "По умолчанию 1, если количество устраивает, введите 'y':"
        );
        config.put(bundle.getString("number_of_wins"), SC.nextLine());

        System.out.println("Кто начинает игру первым. Если игрок введите 'user', если машина 'machine':");
        config.put(bundle.getString("first_move"), SC.nextLine());
    }

    public Map<String, String> getConfig() {
        return config;
    }
}
