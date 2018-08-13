package ru.inurgalimov.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Check work method hasCycle().
 *
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class MyNodeTest {
    @Test
    public void whenCheckTheCycle() {
        MyNode first = new MyNode(1);
        MyNode two = new MyNode(2);
        MyNode third = new MyNode(3);
        MyNode four = new MyNode(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
        assertThat(MyNode.hasCycle(first), is(true));
    }

    @Test
    public void whenCheckTheNoCycle() {
        MyNode first = new MyNode(1);
        MyNode two = new MyNode(2);
        MyNode third = new MyNode(3);
        MyNode four = new MyNode(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(new MyNode(5));
        assertThat(MyNode.hasCycle(first), is(false));
    }

}