package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.HashMap;

/**
 * Хранилище для склидрования.
 *
 * @author Ilshat Nurgalimov.
 * @since 03.06.2019
 */
public class Warehouse implements Storage {

    /**
     * Процент выше которого продукт не добавляется в хранилище.
     */
    private final static int LIMIT_PERCENT = 25;

    /**
     * Контейнер для хранения продуктов.
     */
    private HashMap<String, Food> foods = new HashMap<>();

    /**
     * Метод добавления в хранилище.
     *
     * @param food    - продукт питания подлежащий хранению.
     * @return - статус добавления продукта в хранилище.
     */
    @Override
    public boolean add(Food food) {
        double percent = 100 - (
                ((food.getExpaireDate().getTime() - System.currentTimeMillis()) * 100)
                        / (food.getExpaireDate().getTime() - food.getCreateDate().getTime())
        );
        boolean result = false;
        if (percent < 25) {
            result = true;
            foods.put(food.getName(), food);
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
        return foods.get(food.getName()) != null;
    }
}
