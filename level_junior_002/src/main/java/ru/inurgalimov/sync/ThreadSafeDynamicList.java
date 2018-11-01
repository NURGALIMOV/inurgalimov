package ru.inurgalimov.sync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.inurgalimov.list.DynamicList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class ThreadSafeDynamicList<E> implements Iterable<E> {
    @GuardedBy("this")
    private volatile DynamicList<E> dynamicList;

    public ThreadSafeDynamicList() {
        dynamicList = new DynamicList<E>();
    }

    public synchronized void add(E value) {
        dynamicList.add(value);
    }

    public synchronized E get(int i) {
        return dynamicList.get(i);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(this.dynamicList).iterator();
    }

    private synchronized List<E> copy(DynamicList dl) {
        List<E> tempArray = new ArrayList<E>();
        for (Object ob : dl) {
            tempArray.add((E) ob);
        }
        return tempArray;
    }
}
