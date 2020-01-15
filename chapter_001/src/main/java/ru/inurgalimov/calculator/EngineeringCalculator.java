package ru.inurgalimov.calculator;

/**
 * Реализация инженерного калькулятора на базе класса Calculator.
 *
 * @author Ilshat Nurgalimov
 * @since 17.05.2019
 */

public class EngineeringCalculator implements Calculatable {

    private final Calculator calculator;

    public EngineeringCalculator() {
        calculator = new Calculator();
    }

    /**
     * Суммируем два числа.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void add(Parametr p) {
        calculator.add(p);
    }

    /**
     * Находим разницу двух чисел.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void subtract(Parametr p) {
        calculator.subtract(p);
    }

    /**
     * Делим два числа.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void div(Parametr p) {
        calculator.div(p);
    }

    /**
     * Умножаем два числа.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void multiple(Parametr p) {
        calculator.multiple(p);
    }

    /**
     * Возводим число в степень.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void pow(Parametr p) {
        calculator.result = Math.pow(p.getFirst(), p.getSecond());
    }

    /**
     * Получаем квадратный корень числа.
     *
     * @param p - контейнер для параметров
     */
    @Override
    public void sqrt(Parametr p) {
        calculator.result = Math.sqrt(p.getFirst());
    }

    /**
     * Получаем Sin угла.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void sin(Parametr p) {
        calculator.result = Math.sin(p.getFirst());
    }

    /**
     * Получаем Tan угла.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void tan(Parametr p) {
        calculator.result = Math.tan(p.getFirst());
    }

    /**
     * Получаем Cos угла.
     *
     * @param p - контейнер для параметров.
     */
    @Override
    public void cos(Parametr p) {
        calculator.result = Math.cos(p.getFirst());
    }

    /**
     * Возвращаем результат вычислений.
     *
     * @return - результат вычислений
     */
    @Override
    public double getResult() {
        return calculator.result;
    }
}
