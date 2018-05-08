package ru.inurgalimov.loop;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {

    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 9);
        int expected = 20;
        assertThat(result, is(expected));
    }
}
