package ru.inurgalimov.list;

import java.util.NoSuchElementException;

public class SimpleQueueNew<T> {
    private SimpleStack<T> pollStack;
    private SimpleStack<T> pushStack;
    private int pollSize;
    private int pushSize;

    public SimpleQueueNew() {
        pollStack = new SimpleStack<T>();
        pushStack = new SimpleStack<T>();
        pollSize = 0;
        pushSize = 0;
    }

    public T poll() {
        if ((pollSize == 0) && (pushSize != 0)) {
            for (; pushSize > 0; pushSize--, pollSize++) {
                pollStack.push(pushStack.poll());
            }
        } else if ((pollSize == 0) && (pushSize == 0)) {
            throw new NoSuchElementException();
        }
        pollSize--;
        return pollStack.poll();
    }

    public void push(T value) {
        pushStack.push(value);
        pushSize++;
    }
}
