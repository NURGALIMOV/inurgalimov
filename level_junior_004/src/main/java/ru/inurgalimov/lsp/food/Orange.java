package ru.inurgalimov.lsp.food;

/**
 * Класс описывает апельсин как продукт.
 *
 * @author Ilshat Nurgalimov
 * @since 29.05.2019
 */
public class Orange extends Food {

    private final static long EXPAIRE = 2592000000L;

    /**
     * Конструктор.
     *
     * @param name  - наименование продукта.
     * @param price - стоимость продукта.
     */
    public Orange(String name, int price) {
        super(name, EXPAIRE, price, false);
    }
}
