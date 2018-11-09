package ru.inurgalimov.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenUseThreadPool() {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            threadPool.work(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("Hello from " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException iEx) {
                        iEx.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
        threadPool.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}