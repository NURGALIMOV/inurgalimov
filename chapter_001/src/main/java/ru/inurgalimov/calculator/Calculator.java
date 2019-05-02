package ru.inurgalimov.calculator;

/**
 * @author Ilshat Nurgalimov
 * @since 13.05.2019
 */
public class Calculator {
    private double result;

    //сумма
    public void add(Parametr p) {
        this.result = p.getFirst() + p.getSecond();
    }

    //Вычитание
    public void subtract(Parametr p) {
        this.result = p.getFirst() - p.getSecond();
    }

    //деление
    public void div(Parametr p) {
        this.result = p.getFirst() / p.getSecond();
    }

    //умножение
    public void multiple(Parametr p) {
        this.result = p.getFirst() * p.getSecond();
    }

    public double getResult() {
        return this.result;
    }
}
