package ru.inurgalimov.lsp;

import ru.inurgalimov.lsp.food.Food;
import ru.inurgalimov.lsp.storage.Storage;


import java.util.ArrayList;
import java.util.List;

/**
 * Класс обработчик перераспределения продуктов в место использования.
 *
 * @author Ilshat Nurgalimov
 * @since 03.06.2019
 */
public class ControllQuality {
    /**
     * Список хранилищ.
     */
    private List<Storage> stores = new ArrayList<>();

    /**
     * Распределение продуктов по хранилищам.
     *
     * @param food продукт.
     */
    public void transfer(Food food) {
        for (Storage s : stores) {
            s.add(food);
        }
    }

    /**
     * Добавление хранилища в список.
     *
     * @param storage - хранилище.
     */
    public void add(Storage storage) {
        stores.add(storage);
    }
}
