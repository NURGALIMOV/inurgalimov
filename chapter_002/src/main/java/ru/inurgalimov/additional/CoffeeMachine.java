package ru.inurgalimov.additional;

import java.util.Arrays;

public class CoffeeMachine {
    private final int COIN1 = 1;
    private final int COIN2 = 2;
    private final int COIN5 = 5;
    private final int COIN10 = 10;

    public int[] changes(int value, int price) {
        int balance = value - price;
        int[] temp = new int[100];
        int index = 0;
        for (; balance != 0; index++) {
            if (balance >= COIN10) {
                balance = balance - COIN10;
                temp[index] = COIN10;
                continue;
            } else if (balance >= COIN5) {
                balance = balance - COIN5;
                temp[index] = COIN5;
                continue;
            } else if (balance >= COIN2) {
                balance = balance - COIN2;
                temp[index] = COIN2;
                continue;
            } else if (balance >= COIN1) {
                balance = balance - COIN1;
                temp[index] = COIN1;
                continue;
            }
        }
        return Arrays.copyOf(temp, index);
    }
}
