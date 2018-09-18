package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleQueueNew<T> {
    private SimpleStack<T> pollStack;
    private SimpleStack<T> pushStack;

    public SimpleQueueNew() {
        pollStack = new SimpleStack<T>();
        pushStack = new SimpleStack<T>();
    }

    public T poll() {
        if ((pollStack.getSize() == 0) && (pushStack.getSize() != 0)) {
            while (pushStack.getSize() > 0) {
                pollStack.push(pushStack.poll());
            }
        } else if ((pollStack.getSize() == 0) && (pushStack.getSize() == 0)) {
            throw new NoSuchElementException();
        }
        return pollStack.poll();
    }

    public void push(T value) {
        pushStack.push(value);
    }
}
