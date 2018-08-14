package ru.inurgalimov.set;

import ru.inurgalimov.list.DynamicList;

import java.util.Iterator;

/**
 * My version Set.
 *
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicList<E> simpleList;

    public SimpleSet() {
        simpleList = new DynamicList<>();
    }

    public boolean add(E value) {
        boolean result = true;
        for (E element : simpleList) {
            if ((element != null) && (element.equals(value))) {
                result = false;
                break;
            }
        }

        if (result) {
            simpleList.add(value);
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return simpleList.iterator();
    }
}
