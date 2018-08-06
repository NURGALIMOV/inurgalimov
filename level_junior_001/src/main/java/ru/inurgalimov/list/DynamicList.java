package ru.inurgalimov.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    private Object[] dynamicList;
    private int index;
    private int modCount;

    public DynamicList() {
        this.dynamicList = new Object[10];
        this.index = 0;
        this.modCount = 0;
    }

    public DynamicList(int size) {
        this.dynamicList = new Object[size];
        this.index = 0;
        this.modCount = 0;
    }

    public void add(E value) {
        if (index >= dynamicList.length) {
            increaseTheSize();
        }
        dynamicList[index++] = value;
        modCount++;
    }

    public E get(int i) {
        return (E) dynamicList[i];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final Object[] arrayForIterator = dynamicList;
            private int count = 0;
            private final int iteratorModCount = modCount;

            @Override
            public boolean hasNext() {
                if (iteratorModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < arrayForIterator.length;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) arrayForIterator[count++];
            }
        };
    }
    private void increaseTheSize() {
        Object[] tempArray = new Object[dynamicList.length * 2];
        System.arraycopy(dynamicList, 0, tempArray, 0, dynamicList.length);
        dynamicList = tempArray;
    }
}

