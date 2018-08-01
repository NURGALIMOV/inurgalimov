package ru.inurgalimov.generic;

import java.util.Iterator;


/**
 * Your List option, the list is not dynamic
 *
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class SimpleArray<T> implements Iterable {
    private Object[] simpleArray;
    private int index;

    public SimpleArray(int size) {
        simpleArray = new Object[size];
        this.index = 0;
    }

    public void add(T model) throws ArrayIndexOutOfBoundsException {
        simpleArray[index++] = model;
    }

    public boolean set(int i, T model) {
        boolean check = false;
        if (i < simpleArray.length) {
            check = true;
            simpleArray[i] = model;
        }
        return check;
    }

    public boolean delete(int i) {
        boolean check = false;
        if (i < simpleArray.length) {
            check = true;
            simpleArray[i] = null;
        }
        return check;
    }

    public T get(int i) {
        T t = null;
        if (i < simpleArray.length) {
            t = (T) simpleArray[i];
        }
        return t;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {
            private int ind = 0;
            private Object[] iteratorArray = simpleArray;

            @Override
            public boolean hasNext() {
                return ind < iteratorArray.length;
            }

            @Override
            public T next() {
                return (T) iteratorArray[ind++];
            }
        };
    }
}
