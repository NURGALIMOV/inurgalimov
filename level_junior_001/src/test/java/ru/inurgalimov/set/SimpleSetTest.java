package ru.inurgalimov.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddElement() {
        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        assertThat(simpleSet.add(1), is(true));
        assertThat(simpleSet.add(2), is(true));
        assertThat(simpleSet.add(3), is(true));
        assertThat(simpleSet.add(1), is(false));
        assertThat(simpleSet.add(2), is(false));
        assertThat(simpleSet.add(3), is(false));
        assertThat(simpleSet.add(4), is(true));
    }

}