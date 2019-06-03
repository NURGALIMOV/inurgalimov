package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.HashMap;

/**
 * Холодный склад.
 *
 * @author Ilshat Nurgalimov
 * @since 03.06.2019
 */

public class ColdWarehouse implements Storage {
    /**
     * Склад.
     */
    private Warehouse warehouse;

    /**
     * Контейнер для хранения продуктов.
     */
    private HashMap<String, Food> fridge = new HashMap<>();

    /**
     * Конструктор.
     *
     * @param warehouse - склад.
     */
    public ColdWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Метод добавления в хранилище.
     *
     * @param food - продукт питания подлежащий хранению.
     * @return - статус добавления продукта в хранилище.
     */
    @Override
    public boolean add(Food food) {
        boolean result = warehouse.add(food);
        if (result) {
            fridge.put(food.getName(), food);
        }
        return result;
    }

    /**
     * Проверка имени продукта.
     *
     * @param food - проевряемый продукт.
     * @return - статус проверки.
     */
    @Override
    public boolean check(Food food) {
        return fridge.get(food.getName()) != null;
    }
}
