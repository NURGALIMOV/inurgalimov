package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> extends SimpleLinkedList<T> {
    public SimpleStack() {
        super();
    }

    public T poll() {
        return dropLast();
    }

    public void push(T value) {
        add(value);
    }
}
