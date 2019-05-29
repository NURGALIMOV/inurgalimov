package ru.inurgalimov.lsp.food;

/**
 * Класс описывает молоко как продукт.
 *
 * @author Ilshat Nurgalimov
 * @since 29.05.2019
 */
public class Milk extends Food {
    private final static long EXPAIRE = 864000000L;

    /**
     * Конструктор.
     *
     * @param name  - наименование продукта.
     * @param price - стоимость продукта.
     */
    public Milk(String name, int price) {
        super(name, EXPAIRE, price);
    }
}
