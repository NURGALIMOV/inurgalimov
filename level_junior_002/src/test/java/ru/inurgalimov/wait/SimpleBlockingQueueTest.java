package ru.inurgalimov.wait;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenUseBlockingQueue() {
        SimpleBlockingQueue<Integer> testQueue = new SimpleBlockingQueue<Integer>();

        Thread producer = new Thread(() -> {
            for (int i = 0; i <= 20;) {
                testQueue.offer(++i);
                System.out.println(Thread.currentThread().getName() + " вставляет " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Thread.currentThread().interrupt();
        });
        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 20; i++) {
                System.out.println(Thread.currentThread().getName() + " вытаскивает " + testQueue.poll());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.currentThread().interrupt();
        });
        consumer.start();
        producer.start();
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}