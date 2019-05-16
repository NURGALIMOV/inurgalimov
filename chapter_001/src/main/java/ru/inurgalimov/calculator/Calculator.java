package ru.inurgalimov.calculator;

/**
 * @author Ilshat Nurgalimov
 * @since 17.05.2019
 */
public class Calculator implements Calculatable {
    protected double result;

    //сумма
    @Override
    public void add(Parametr p) {
        this.result = p.getFirst() + p.getSecond();
    }

    //Вычитание
    @Override
    public void subtract(Parametr p) {
        this.result = p.getFirst() - p.getSecond();
    }

    //деление
    @Override
    public void div(Parametr p) {
        this.result = p.getFirst() / p.getSecond();
    }

    //умножение
    @Override
    public void multiple(Parametr p) {
        this.result = p.getFirst() * p.getSecond();
    }

    @Override
    public void pow(Parametr p) {

    }

    @Override
    public void sqrt(Parametr p) {

    }

    @Override
    public void sin(Parametr p) {

    }

    @Override
    public void tan(Parametr p) {

    }

    @Override
    public void cos(Parametr p) {

    }

    @Override
    public double getResult() {
        return this.result;
    }
}
