package ru.inurgalimov.loop;

/**
 * Test class for Counter
 * @Nurgalimov Ilshat
 * @since 08.05.2018
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    private Counter counter = new Counter();

    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        int result = counter.add(1, 9);
        int expected = 20;
        assertThat(result, is(expected));
    }
}
