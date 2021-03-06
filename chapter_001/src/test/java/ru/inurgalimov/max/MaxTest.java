package ru.inurgalimov.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class MaxTest {
    private Max maxim = new Max();
    @Test
    public void whenFirstLessSecond() {
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenFirstLessSecondLessThird() {
        int result = maxim.max(1, 2, 3);
        assertThat(result, is(3));
    }
}

