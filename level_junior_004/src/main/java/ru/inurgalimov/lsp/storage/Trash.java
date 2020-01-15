package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Мусорка
 *
 * @author Ilshat Nurgalimov
 * @since 05.06.2019
 */
public class Trash implements Storage {

    /**
     * Процент выше которого продукт добавляется в хранилище.
     */
    private final static int LIMIT_PERCENT = 100;

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
        if (percent >= LIMIT_PERCENT) {
            result = true;
            this.foods.put(food.getName(), food);
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
        return this.foods.get(food.getName()) != null;
    }

    /**
     * Повторная проверка продуктов.
     *
     * @return - возвращается список продуктов не прошедших повторную проверку.
     */
    @Override
    public List<Food> resort() {
        List<Food> result = new ArrayList<>();
        for (Food food : foods.values()) {
            if (!add(food)) {
                foods.remove(food.getName());
                result.add(food);
            }
        }
        return result;
    }
}
