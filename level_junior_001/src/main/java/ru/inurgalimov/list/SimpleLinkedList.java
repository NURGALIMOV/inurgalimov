package ru.inurgalimov.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E> {
    protected Node<E> head;
    protected Node<E> tail;
    protected int count;

    public SimpleLinkedList() {
        this.count = 0;
    }

    public void add(E value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.nextStep = node;
            tail = node;
        }
        count++;
    }

    public E get(int index) {
        Node<E> resultNode = null;
        if (index < count) {
            resultNode = head;
            for (int i = 1; i <= index; i++) {
                resultNode = resultNode.nextStep;
            }
        } else {
            throw new NoSuchElementException();
        }
        return resultNode.e;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iteratorStep = 0;
            private final int modCount = count;
            private Node<E> iteratorNode;

            @Override
            public boolean hasNext() {
                if (count != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iteratorStep < count;
            }

            @Override
            public E next() {
                while (hasNext()) {
                    if (iteratorNode == null) {
                        iteratorNode = head;
                        iteratorStep++;
                        break;
                    }
                    iteratorNode = iteratorNode.nextStep;
                    iteratorStep++;
                    break;
                }

                return iteratorNode.e;
            }
        };
    }

    protected class Node<E> {
        private E e;
        private Node<E> nextStep;
        private Node<E> lastStep;

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public Node<E> getNextStep() {
            return nextStep;
        }

        public void setNextStep(Node<E> nextStep) {
            this.nextStep = nextStep;
        }

        public Node<E> getLastStep() {
            return lastStep;
        }

        public void setLastStep(Node<E> lastStep) {
            this.lastStep = lastStep;
        }

        public Node(E e) {
            this.e = e;
        }
    }
}
