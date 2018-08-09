package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> extends NewLinkedList<T> {

    public SimpleQueue() {
        super();
    }

    public T poll() {
        if (this.getHead() == null) {
            throw new NoSuchElementException();
        }
        Node node = this.getHead();
        this.setHead(this.getHead().getNextStep());
        return (T) node.getE();
    }

    public void push(T value) {
        this.add(value);
    }

}
