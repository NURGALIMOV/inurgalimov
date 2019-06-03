package ru.inurgalimov.lsp.food;

import java.util.Date;

/**
 * Класс описывает продукты питания.
 *
 * @author Ilshat Nurgalimov.
 * @since 29.05.2019
 */
public abstract class Food {

    /**
     * Наименование продукта.
     */
    protected String name;

    /**
     * Срок хранения.
     */
    protected Date expaireDate;

    /**
     * Дата изготовления.
     */
    protected Date createDate;

    /**
     * Стоимость.
     */
    protected int price;

    /**
     * Скидка на товар.
     */
    protected int disscount;

    /**
     * Возможность переработки.
     */
    protected boolean canReproduct;

    /**
     * Конструктор.
     *
     * @param name    - наименование продукта.
     * @param expaire - время в миллисекундах истечения срока хранения с даты изготовления.
     * @param price   - стоимость продукта.
     * @param canReproduct   - возможность переработки.
     */
    public Food(String name, long expaire, int price, boolean canReproduct) {
        this.name = name;
        this.createDate = new Date();
        this.expaireDate = new Date(createDate.getTime() + expaire);
        this.price = price;
        this.disscount = 0;
        this.canReproduct = canReproduct;
    }



    public String getName() {
        return name;
    }

    public Date getExpaireDate() {
        return expaireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDisscount() {
        return disscount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDisscount(int disscount) {
        this.disscount = disscount;
    }

    public boolean isCanReproduct() {
        return canReproduct;
    }
}
