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


    public SimpleBlockingQueue() {
        queue = new LinkedList<T>();
        size = 0;
    }


    public void offer(T value) {
        synchronized (this) {
            while (size == maxSize) {
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Thread.currentThread().interrupted()) {
                    break;
                }
            }
            size++;
            queue.offer(value);
        }
    }

    public T poll() {
        synchronized (this) {
            while (size == minSize) {
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Thread.currentThread().interrupted()) {
                    break;
                }
            }
            T t = queue.poll();
            --size;
            return t;
        }
    }
}
