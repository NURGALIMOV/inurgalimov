package ru.inurgalimov.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {
    @Test
    public void whenWeUseTheStack() {
        SimpleStack<Integer> simpleStack = new SimpleStack<>();
        simpleStack.push(1);
        simpleStack.push(2);
        simpleStack.push(3);
        int result = simpleStack.poll();
        assertThat(result, is(3));
        result = simpleStack.poll();
        assertThat(result, is(2));
        result = simpleStack.poll();
        assertThat(result, is(1));
    }

}