package ru.inurgalimov.wait;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest2 {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                    queue.setChange(true);
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

//    @Test
//    public void whenTheListIsEmpty() throws InterruptedException {
//        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
//        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
//        Thread producer = new Thread(
//                () -> {
//                    IntStream.range(0, 5).forEach(numb -> queue.offer(numb));
//                    queue.setChange(true);
//                }
//        );
//
//        Thread consumer = new Thread(
//                () -> {
//                    do {
//                        try {
//                            buffer.add(queue.poll());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Thread.currentThread().interrupt();
//                        }
//                    } while (!queue.isEmpty() || !Thread.currentThread().isInterrupted());
//                }
//        );
//        consumer.start();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        producer.start();
//        producer.join();
//        consumer.interrupt();
//        consumer.join();
//        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
//    }
}
