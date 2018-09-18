package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> extends SimpleLinkedList<T> {

    private int size;

    public SimpleStack() {
        super();
        size = 0;
    }

    public T poll() {
        size--;
        return dropLast();
    }

    public void push(T value) {
        add(value);
        size++;
    }

    public int getSize() {
        return size;
    }
}
