package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> extends SimpleLinkedList<T> {
    public SimpleStack() {
        super();
    }

    public T poll() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        Node n = tail;
        tail = tail.getLastStep();
        return (T) n.getE();
    }

    public void push(T value) {
        add(value);
    }

    @Override
    public void add(T value) {
        Node n = new Node(value);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            Node temp = tail;
            tail.setNextStep(n);
            tail = n;
            tail.setLastStep(temp);
        }
        count++;
    }
}
