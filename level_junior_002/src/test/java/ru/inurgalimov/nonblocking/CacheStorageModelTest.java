package ru.inurgalimov.nonblocking;

import static org.hamcrest.Matchers.is;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class CacheStorageModelTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        CacheStorageModel cacheStorageModel = new CacheStorageModel();
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    cacheStorageModel.add(new Base(1, 1));
                    cacheStorageModel.add(new Base(2, 1));
                    cacheStorageModel.add(new Base(3, 1));
                    try {
                        for (int i = 0; i < 10000; i++) {
                            cacheStorageModel.update(new Base(2, 1));
                        }
                    } catch (OptimisticException oEx) {
                        ex.set(oEx);
                    }

                }
        );
        Thread thread2 = new Thread(
                () -> {
                    cacheStorageModel.add(new Base(4, 1));
                    cacheStorageModel.add(new Base(5, 1));
                    cacheStorageModel.add(new Base(6, 1));
                    try {
                        for (int i = 0; i < 10000; i++) {
                            cacheStorageModel.update(new Base(2, 1));
                        }
                    } catch (OptimisticException oEx) {
                        ex.set(oEx);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("The current version of the model differs by more than one."));
    }
}