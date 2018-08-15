package ru.inurgalimov.list;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
    }
    @Test
    public void whenAddAndAfterGetTheElements() {
        Integer result = list.get(0);
        assertThat(result, is(1));
        result = list.get(1);
        assertThat(result, is(2));
        result = list.get(2);
        assertThat(result, is(3));

    }
    @Test
    public void whenCallingAnIterator() {
        Iterator<Integer> iterator = list.iterator();
        Integer result = iterator.next();
        assertThat(result, is(1));
        result = iterator.next();
        assertThat(result, is(2));
        result = iterator.next();
        assertThat(result, is(3));
    }
}
