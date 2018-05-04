package ru.inurgalimov.calculator;

public class Calculator {
    private double result;

    //сумма
    public void add(double first, double second) {
        this.result = first + second;
    }

    //Вычитание
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    //деление
    public void div(double first, double second) {
        this.result = first / second;
    }

    //умножение
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }
}
