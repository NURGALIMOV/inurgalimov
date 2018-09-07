package ru.inurgalimov.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;
    private Node<E> parent;
    private int indexForIterator;

    public Node(final E value) {
        this.value = value;
        indexForIterator = 0;
    }

    public void add(Node<E> child) {
        child.parent = this;
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    public E getValue() {
        return value;
    }

    public Node<E> getParent() {
        return parent;
    }

    public int getIndexForIterator() {
        return indexForIterator;
    }

    public void setIndexForIterator(int indexForIterator) {
        this.indexForIterator = indexForIterator;
    }
}
