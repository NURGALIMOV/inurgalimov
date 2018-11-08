package ru.inurgalimov.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue;
    private volatile int size;
    private final int minSize = 0;
    private final int maxSize = 10;
    private volatile boolean change;


    public SimpleBlockingQueue() {
        queue = new LinkedList<T>();
        size = 0;
    }


    public void offer(T value) {
        synchronized (this) {
            if (size == maxSize) {
                setChange(true);
            }
            while (change || Thread.currentThread().isInterrupted()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            size++;
            queue.offer(value);
        }
    }

    public T poll() {
        synchronized (this) {
            if (this.isEmpty()) {
                setChange(false);
            }
            while (!change || !Thread.currentThread().isInterrupted()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            T t = queue.poll();
            --size;
            return t;
        }
    }

    public void setChange(boolean change) {
        synchronized (this) {
            this.change = change;
            this.notify();
        }
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
