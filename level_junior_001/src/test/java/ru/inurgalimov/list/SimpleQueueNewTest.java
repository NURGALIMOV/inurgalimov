package ru.inurgalimov.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueNewTest {
    @Test
    public void whenCheckWorkQueue() {
        SimpleQueueNew<Integer> queue = new SimpleQueueNew<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        queue.push(4);
        assertThat(queue.poll(), is(3));
        queue.push(5);
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
    }

}