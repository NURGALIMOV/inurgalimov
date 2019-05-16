package ru.inurgalimov.calculator;

/**
 * Интерфейс калькулятора.
 * @author Ilshat Nurgalimov
 * @since 17.05.2019
 */
public interface Calculatable {

    void add(Parametr p);

    void subtract(Parametr p);

    void div(Parametr p);

    void multiple(Parametr p);

    void pow(Parametr p);

    void sqrt(Parametr p);

    void sin(Parametr p);

    void tan(Parametr p);

    void cos(Parametr p);

    double getResult();
}
