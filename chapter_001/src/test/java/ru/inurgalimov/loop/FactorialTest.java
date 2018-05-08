package ru.inurgalimov.loop;

/**
 * Test class for Factorial
 *
 * @author Nurgalimov Ilshat
 * @since 08.05.2018
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    private Factorial factorial = new Factorial();

    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        //напишите здесь тест, проверяющий, что факториал для числа 5 равен 120.
        int result = factorial.calc(5);
        int expected = 120;
        assertThat(result, is(expected));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        //напишите здесь тест, проверяющий, что факториал для числа 0 равен 1.
        int result = factorial.calc(0);
        int expected = 1;
        assertThat(result, is(expected));
    }
}
