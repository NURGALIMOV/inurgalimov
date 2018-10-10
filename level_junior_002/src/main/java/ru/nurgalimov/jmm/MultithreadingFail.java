package main.java.ru.nurgalimov.jmm;

public class MultithreadingFail {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Thread thr1 = new Thread(new MyThread(resource));
        Thread thr2 = new Thread(new MyThread(resource));
        Thread thr3 = new Thread(new MyThread(resource));

        thr1.start();
        thr2.start();
        thr3.start();

        try {
            thr1.join();
            thr2.join();
            thr3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
