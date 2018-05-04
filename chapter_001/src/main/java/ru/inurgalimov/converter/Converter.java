package ru.inurgalimov.converter;

/**
 * Корвертор валюты.
 *
 * @author Ilshat Nurgalimov
 * @since 04.05.2018
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     *
     * @param value рубли.
     * @return Евро.
     */

    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Конвертируем рубли в доллары.
     *
     * @param value рубли.
     * @return Доллоры
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }
    //2. Добавь еще два методы для обратно конвертации евро и долларов в рубли.

    /**
     * Конвертируем евро в рубли
     *
     * @param value евро
     * @return рубли
     */
    public int euroToRuble(int value) {
        return value * 70;
    }

    /**
     * Конвертируем доллары в рубли
     *
     * @param value доллары
     * @return рубли
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }
}
