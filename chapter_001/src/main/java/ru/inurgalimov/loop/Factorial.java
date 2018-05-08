package ru.inurgalimov.loop;
/**
 * calculate the factorial
 * @author Nurgalimov Ilshat
 * @since 08.05.2018
 */

public class Factorial {
    public int calc(int n) {
        int factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }
}
