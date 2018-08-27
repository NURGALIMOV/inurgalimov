package ru.inurgalimov.tree;

import java.util.*;

/**
 * @param <E>
 * @author Ilshat Nurgalimov
 * @version 1.0
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public Tree(E e) {
        this.root = new Node<E>(e);
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final Node<E> ITNODE = root;
            private Queue<Node<E>> queue = fillQueue(ITNODE);

            public Queue<Node<E>> fillQueue(Node<E> eNode) {
                if (queue == null) {
                    queue = new LinkedList<>();
                }
                queue.add(eNode);
                if (!eNode.leaves().isEmpty()) {
                    for (Node<E> n : eNode.leaves()) {
                        if (!n.leaves().isEmpty()) {
                            fillQueue(n);
                        } else {
                            queue.add(n);
                        }
                    }
                }
                return queue;
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                return queue.poll().getValue();
            }
        };
    }
}
