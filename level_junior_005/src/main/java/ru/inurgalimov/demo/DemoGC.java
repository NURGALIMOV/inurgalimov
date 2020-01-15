package ru.inurgalimov.demo;

import java.lang.ref.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class DemoGC {

    public static class User {
        String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            new User("a");
        }
        long end = System.currentTimeMillis();
        long result = end - start;
        System.out.println(result);
    }
}
