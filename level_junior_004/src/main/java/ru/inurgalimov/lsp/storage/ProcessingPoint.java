package ru.inurgalimov.lsp.storage;

import ru.inurgalimov.lsp.food.Food;

import java.util.HashMap;

/**
 * Центр переработки.
 *
 * @author Ilshat Nurgalimov
 * @since 03.06.2019
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
        if(result && food.isCanReproduct()) {
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
}
