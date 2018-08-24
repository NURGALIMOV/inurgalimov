package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> extends SimpleLinkedList<T> {

    public SimpleQueue() {
        super();
    }

    public T poll() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node node = head;
        head = head.getNextStep();
        return (T) node.getE();
    }

    public void push(T value) {
        add(value);
    }

}
