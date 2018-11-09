package ru.inurgalimov.pool;

import ru.inurgalimov.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            this.threads.add(new Thread(() -> {
                while (this.tasks.isEmpty() && !Thread.currentThread().isInterrupted()) {
                    synchronized (this.tasks) {
                        try {
                            this.tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }

                }
                this.tasks.poll().run();
            }));
            this.threads.get(i).start();
        }
    }

    public void work(Runnable job) {
        synchronized (this.tasks) {
            this.tasks.offer(job);
            this.tasks.setChange(true);
            this.tasks.notifyAll();
        }
    }

    public void shutdown() {
        for (Thread th : this.threads) {
            th.interrupt();
        }
    }

    public void joinForThreadPool() {
        for (Thread th : this.threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                th.interrupt();
            }
        }
    }
}
