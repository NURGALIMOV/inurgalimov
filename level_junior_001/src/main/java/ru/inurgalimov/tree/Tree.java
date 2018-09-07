package ru.inurgalimov.tree;

import java.util.*;

/**
 * @param <E>
 * @author Ilshat Nurgalimov
 * @version 1.0
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount;

    public Tree(E e) {
        this.root = new Node<E>(e);
        modCount = 0;
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> temp = findBy(parent);
        boolean result = temp.isPresent();
        for (Node n : temp.get().leaves()) {
            if (n.eqValue(child)) {
                result = false;
            }
        }
        if (result) {
            temp.get().add(new Node<>(child));
        }
        modCount++;
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
        boolean result = true;
        Optional<Node<E>> optional = Optional.empty();
        Queue<Node<E>> checkQueue = new LinkedList<>();
        checkQueue.offer(this.root);
        while (!checkQueue.isEmpty()) {
            Node<E> temp = checkQueue.poll();
            if (temp.leaves().size() > 2) {
                result = false;
                break;
            }
            for (Node<E> child : temp.leaves()) {
                checkQueue.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> rootIterator = root;
            private int index = 0;
            private final int modCountIterator = modCount;

            @Override
            public boolean hasNext() {
                if (modCountIterator != modCount) {
                    throw new ConcurrentModificationException();
                }
                return rootIterator != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E e = rootIterator.getValue();
                if (!rootIterator.leaves().isEmpty()) {
                    index = 0;
                    rootIterator = rootIterator.leaves().get(index);
                } else {
                    do {
                        rootIterator.setIndexForIterator(0);
                        rootIterator = rootIterator.getParent();
                        index = rootIterator.getIndexForIterator() + 1;
                        rootIterator.setIndexForIterator(index);
                        if (index >= rootIterator.leaves().size() && rootIterator == root) {
                            rootIterator.setIndexForIterator(0);
                            rootIterator = null;
                            break;
                        }
                    } while (index >= rootIterator.leaves().size() && rootIterator.getParent() != null);
                    if (hasNext()) {
                        rootIterator = rootIterator.leaves().get(index);
                    }
                }
                return e;
            }
        };
    }
}
