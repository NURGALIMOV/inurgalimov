package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Магазин
 *
 * @author Ilshat Nurgalimov
 * @since 05.06.2019
 */
public class Shop implements Storage {
    /**
     * Минималный процент израсходования срока годности для добавления в хранилище.
     */
    private final static int MIN_PERCENT = 25;

    /**
     * Процент при котором добавляется скидка.
     */
    private final static int MEDIUM_PERCENT = 75;
    /**
     * Максимальный процент израсходования срока годности для добавления в хранилище.
     */
    private final static int MAX_PERCENT = 100;

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
        if (percent >= MIN_PERCENT && percent < MAX_PERCENT) {
            result = true;
            if (percent >= MEDIUM_PERCENT) {
                food.setDisscount(MIN_PERCENT);
            }
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
