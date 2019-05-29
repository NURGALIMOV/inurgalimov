package ru.inurgalimov.lsp.food;

/**
 * Класс описывает яблоко как продукт.
 *
 * @author Ilshat Nurgalimov
 * @since 29.05.2019
 */
public class Apple extends Food {
    private final static long EXPAIRE = 5184000000L;

    /**
     * Конструктор.
     *
     * @param name  - наименование продукта.
     * @param price - стоимость продукта.
     */
    public Apple(String name, int price) {
        super(name, EXPAIRE, price);
    }
}
