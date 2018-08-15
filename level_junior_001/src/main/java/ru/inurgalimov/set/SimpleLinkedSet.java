package ru.inurgalimov.set;

import ru.inurgalimov.list.SimpleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My version LinkedSet.
 *
 * @author Nurgalimov Ilshat
 * @version 2.0
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    private SimpleLinkedList<E> simpleLinkedList;

    public SimpleLinkedSet() {
        this.simpleLinkedList = new SimpleLinkedList<E>();
    }

    public void add(E value) {
        boolean checkAdd = true;
        for (E forCheck : simpleLinkedList) {
            if ((forCheck != null) && (forCheck.equals(value))) {
                checkAdd = false;
                break;
            }
        }
        if (checkAdd) {
            simpleLinkedList.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return simpleLinkedList.iterator();
    }
}
