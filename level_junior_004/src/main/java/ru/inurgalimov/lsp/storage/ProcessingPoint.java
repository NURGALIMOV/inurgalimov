package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Центр переработки.
 *
 * @author Ilshat Nurgalimov
 * @since 05.06.2019
 */
public class ProcessingPoint implements Storage {
    /**
     * Мусорка
     */
    private Trash trash;

    /**
     * Контейнер для хранения продуктов.
     */
    private HashMap<String, Food> recast = new HashMap<>();

    public ProcessingPoint(Trash trash) {
        this.trash = trash;
    }

    /**
     * Метод добавления в хранилище.
     *
     * @param food - продукт питания подлежащий хранению.
     * @return - статус добавления продукта в хранилище.
     */
    @Override
    public boolean add(Food food) {
        boolean result = trash.add(food);
        if (result && food.isCanReproduct()) {
            recast.put(food.getName(), food);
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
        return false;
    }

    /**
     * Повторная проверка продуктов.
     *
     * @return - возвращается список продуктов не прошедших повторную проверку.
     */
    @Override
    public List<Food> resort() {
        List<Food> result = new ArrayList<>();
        for (Food food : recast.values()) {
            if (!add(food)) {
                recast.remove(food.getName());
                result.add(food);
            }
        }
        return result;
    }
}
