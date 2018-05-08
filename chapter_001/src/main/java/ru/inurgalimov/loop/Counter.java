package ru.inurgalimov.loop;

/**
 * count the sum of even numbers
 * @author Nurgalimov Ilshat
 * @since 08.05.2018
 */
public class Counter {
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
