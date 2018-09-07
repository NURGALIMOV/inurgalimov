package ru.inurgalimov.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenUseIteratorForTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(1, 6);
        tree.add(6, 7);
        Iterator<Integer> testIterator = tree.iterator();
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(1));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(2));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(3));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(4));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(5));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(6));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(7));
        assertThat(testIterator.hasNext(), is(false));
    }

    @Test
    public void whenUseIteratorForTree2() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        tree.add(6, 7);
        Iterator<Integer> testIterator = tree.iterator();
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(1));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(2));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(3));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(4));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(5));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(6));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(7));
        assertThat(testIterator.hasNext(), is(false));
    }

    @Test
    public void whenUseIteratorForTree3() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        Iterator<Integer> testIterator = tree.iterator();
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(1));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(2));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(4));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(5));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(3));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(6));
        assertThat(testIterator.hasNext(), is(true));
        assertThat(testIterator.next(), is(7));
        assertThat(testIterator.hasNext(), is(false));

        Iterator<Integer> newIterator = tree.iterator();
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(1));
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(2));
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(4));
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(5));
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(3));
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(6));
        assertThat(newIterator.hasNext(), is(true));
        assertThat(newIterator.next(), is(7));
        assertThat(newIterator.hasNext(), is(false));
    }

    @Test
    public void whenUseIsBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary(), is(true));

    }

    @Test
    public void whenUseNoBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(3, 8);
        assertThat(tree.isBinary(), is(false));
    }
}
