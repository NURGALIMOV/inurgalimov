package ru.inurgalimov.itertor;

import org.junit.Before;
import org.junit.Test;
import ru.inurgalimov.generic.SimpleArray;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleArrayTest {
    private SimpleArray<Integer> test;

    @Before
    public void setUp() {
        test = new SimpleArray<Integer>(3);
    }

    @Test
    public void whenAddElement() {
        test.add(1);
        assertThat(test.get(0), is(1));
    }

    @Test
    public void whenSetElement() {
        test.add(1);
        test.set(0, 2);
        assertThat(test.get(0), is(2));
    }

    @Test
    public void whenDeleteElement() {
        test.add(0);
        test.add(1);
        test.add(2);
        test.delete(1);
        Integer i = null;
        assertThat(test.get(1), is(i));
    }

    @Test
    public void whenUseIterator() {
        test.add(0);
        test.add(1);
        test.add(2);
        Iterator it = test.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat((Integer) it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat((Integer) it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat((Integer) it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

}
