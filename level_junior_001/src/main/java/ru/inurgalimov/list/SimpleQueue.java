package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> extends SimpleLinkedList<T> {

    public SimpleQueue() {
        super();
    }

    public T poll() {
        return dropFirst();
    }

    public void push(T value) {
        add(value);
    }

}
