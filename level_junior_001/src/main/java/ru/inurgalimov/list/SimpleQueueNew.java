package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleQueueNew<T> {
    private SimpleStack<T> out;
    private SimpleStack<T> in;

    public SimpleQueueNew() {
        out = new SimpleStack<T>();
        in = new SimpleStack<T>();
    }

    public T poll() {
        if ((out.getSize() == 0) && (in.getSize() != 0)) {
            while (in.getSize() > 0) {
                out.push(in.poll());
            }
        } else if ((out.getSize() == 0) && (in.getSize() == 0)) {
            throw new NoSuchElementException();
        }
        return out.poll();
    }

    public void push(T value) {
        in.push(value);
    }
}
