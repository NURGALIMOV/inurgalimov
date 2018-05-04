package ru.inurgalimov.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    private Calculator calc = new Calculator();
    @Test
    public void whenAddOnePlusOneThenTwo() {
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractTwoMinusOneThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(2D, 1D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivTwoDivTwoThenOne() {
        calc.div(2D, 2D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultipleTwoMultipleTwoThenFour() {
        calc.multiple(2D, 2D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
}
