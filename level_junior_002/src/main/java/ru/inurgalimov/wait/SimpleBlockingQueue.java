package ru.inurgalimov.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<T>();
    private final int maxSize = 10;
    private volatile boolean change = false;

    public synchronized void offer(T value) {
        if (queue.size() == maxSize) {
            setChange(true);
        }
        while (change && !Thread.currentThread().isInterrupted()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        queue.offer(value);
    }

    public synchronized T poll() {
        if (this.isEmpty()) {
            setChange(false);
        }
        while (!change && !Thread.currentThread().isInterrupted()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        T t = queue.poll();
        return t;
    }

    public synchronized void setChange(boolean change) {
        this.change = change;
        this.notify();
    }

    public synchronized int getSize() {
        return this.queue.size();
    }

    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
