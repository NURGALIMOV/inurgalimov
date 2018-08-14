package ru.inurgalimov.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedSetTest {
    @Test
    public void whenAddElement() {
        SimpleLinkedSet<Integer> simpleLinkedSet = new SimpleLinkedSet<>();
        simpleLinkedSet.add(1);
        simpleLinkedSet.add(1);
        simpleLinkedSet.add(2);
        simpleLinkedSet.add(2);
        simpleLinkedSet.add(3);
        simpleLinkedSet.add(3);
        Iterator iterator = simpleLinkedSet.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(false));
    }
}