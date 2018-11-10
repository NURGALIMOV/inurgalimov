package ru.inurgalimov.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenUseThreadPool() {
        ThreadPool threadPool = new ThreadPool();
        List<Integer> lst = new ArrayList();
        for (int i = 0; i < 8; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException iEx) {
                iEx.printStackTrace();
            }
            threadPool.work(() -> {
                lst.add(1);
                System.out.println("Hello from " + Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
        threadPool.joinForThreadPool();
        assertThat(lst, is(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1)));
    }


}