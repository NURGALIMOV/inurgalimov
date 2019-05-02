package ru.inurgalimov.calculator;

/**
 * Параметр для операций класса Calculator.
 * @author Ilshat Nurgalimov
 * @since 13.05.2019
 */
public class Parametr {
    private final double first;
    private final double second;

    public Parametr(double first, double second) {
        this.first = first;
        this.second = second;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }

}
