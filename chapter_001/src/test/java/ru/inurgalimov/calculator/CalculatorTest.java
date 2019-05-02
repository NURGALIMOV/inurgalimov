package ru.inurgalimov.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(new Parametr(1D, 1D));
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractTwoMinusOneThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(new Parametr(2D, 1D));
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivTwoDivTwoThenOne() {
        Calculator calc = new Calculator();
        calc.div(new Parametr(2D, 2D));
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultipleTwoMultipleTwoThenFour() {
        Calculator calc = new Calculator();
        calc.multiple(new Parametr(2D, 2D));
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
}
