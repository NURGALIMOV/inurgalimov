package ru.inurgalimov.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService exPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String subject = "Notification {" + user.getUsername() + "} to email {" + user.getEmail() + "}";
        String body = "Add a new event to {" + user.getUsername() + "}";
        exPool.submit(() -> send(subject, body, user.getEmail()));
    }

    public void close() {
        exPool.shutdown();
        while (!exPool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {

    }
}
