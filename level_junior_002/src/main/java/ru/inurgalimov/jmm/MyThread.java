package ru.inurgalimov.jmm;

public class MyThread implements Runnable {
    Resource resource;

    public MyThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        resource.printMessage(Thread.currentThread().getName());
    }
}
