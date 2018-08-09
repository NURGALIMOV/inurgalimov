package ru.inurgalimov.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    @Test
    public void whenWeUseTheQueue() {
        SimpleQueue<Integer> testQueue = new SimpleQueue<>();
        testQueue.push(1);
        testQueue.push(2);
        testQueue.push(3);
        assertThat(testQueue.poll(), is(1));
        assertThat(testQueue.poll(), is(2));
        assertThat(testQueue.poll(), is(3));
    }

}