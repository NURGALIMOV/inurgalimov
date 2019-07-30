package ru.inurgalimov.xo;

import java.util.ResourceBundle;

/**
 * Создание игры крестики нолики.
 * Вывод данных в консоль в псевдографики.
 * Пользователь начинает игру. Вводи координаты точки.
 * Предусмотреть, чтобы компьютер мог начать игру.
 * Предусмотреть увеличение поле. По умолчанию используется поле 3 на 3.
 * Предусмотреть усложнение логики игры. Выигрывает тот кто соберет 5 подряд.
 */
public class Game {


    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("keywords");

    public static void main(String[] args) {
        Menu menu = new Menu(BUNDLE);
        menu.show();
        Field field = new Field();
        field.print();
    }
}
