package ru.inurgalimov.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My version LinkedSet.
 *
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    private Node head;
    private Node nextNode;

    public void add(E value) {
        Node tempNode = new Node(value);

        if (head == null) {
            head = tempNode;
            nextNode = tempNode;
        } else {
            boolean noDuplicate = true;
            Node check = head;
            while (true) {
                if (check.value.equals(value)) {
                    noDuplicate = false;
                    break;
                }
                if (check.next == null) {
                    break;
                }
                check = check.next;
            }
            if (noDuplicate) {
                nextNode.next = tempNode;
                nextNode = tempNode;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> nextIterator = head;

            @Override
            public boolean hasNext() {
                boolean hasNext = true;
                if (nextIterator.next == null) {
                    hasNext = false;

                }
                return hasNext;
            }

            @Override
            public E next() {
                Node temp = nextIterator;
                if (hasNext()) {
                    nextIterator = nextIterator.next;
                }
                return (E) temp.value;
            }
        };
    }

    private class Node<E> {
        E value;
        Node next;

        public Node(E value) {
            this.value = value;
        }
    }
}
