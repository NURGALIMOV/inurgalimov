package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

/**
 * Интерфейс для хранилищ.
 *
 * @author Ilshat Nurgalimov.
 * @since 29.05.2019
 */
public interface Storage {

    /**
     * Метод добавления в хранилище.
     *
     * @param food    - продукт питания подлежащий хранению.
     * @return - статус добавления продукта в хранилище.
     */
    boolean add(Food food);

    /**
     * Проверка имени продукта.
     *
     * @param food - проевряемый продукт.
     * @return - статус проверки.
     */
    boolean check(Food food);
}
