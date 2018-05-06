package ru.inurgalimov.max;

/**
 * Возвращаем максимум
 *
 * @author Nurgalimov Ilshat
 * @since 05.05, 2018
 */
public class Max {
    public int max(int first, int second) {
        return first > second ? first : second;
    }
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
