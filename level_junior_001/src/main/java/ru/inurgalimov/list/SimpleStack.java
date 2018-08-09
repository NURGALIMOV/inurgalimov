package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> extends NewLinkedList<T> {
    public SimpleStack() {
        super();
    }

    public T poll() {
        if (getTail() == null) {
            throw new NoSuchElementException();
        }
        Node n = getTail();
        setTail(getTail().getLastStep());
        return (T) n.getE();
    }

    public void push(T value) {
        this.add(value);
    }

    @Override
    public void add(T value) {
        Node n = new Node(value);
        if (getHead() == null) {
            setHead(n);
            setTail(n);
        } else {
            Node temp = getTail();
            getTail().setNextStep(n);
            setTail(n);
            getTail().setLastStep(temp);
        }
        setCount(getCount() + 1);
    }


}
