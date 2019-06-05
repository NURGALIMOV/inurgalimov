package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.List;

/**
 * Интерфейс повторной проверки.
 *
 * @author Ilshat Nurgalimov
 * @since 05.06.2019
 */
public interface Resort {
    /**
     * Повторная проверка продуктов.
     *
     * @return - возвращается список продуктов не прошедших повторную проверку.
     */
    List<Food> resort();
}
