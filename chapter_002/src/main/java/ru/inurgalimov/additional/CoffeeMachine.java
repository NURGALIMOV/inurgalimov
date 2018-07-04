package ru.inurgalimov.additional;

import java.util.Arrays;

public class CoffeeMachine {
    private final int oneCoin = 1;
    private final int twoCoin = 2;
    private final int fiveCoin = 5;
    private final int tenCoin = 10;

    public int[] changes(int value, int price) {
        int balance = value - price;
        int[] temp = new int[100];
        int index = 0;
        for (; balance != 0 && balance > 0; index++) {
            if (balance >= tenCoin) {
                balance = balance - tenCoin;
                temp[index] = tenCoin;
                continue;
            } else if (balance >= fiveCoin) {
                balance = balance - fiveCoin;
                temp[index] = fiveCoin;
                continue;
            } else if (balance >= twoCoin) {
                balance = balance - twoCoin;
                temp[index] = twoCoin;
                continue;
            } else if (balance >= oneCoin) {
                balance = balance - oneCoin;
                temp[index] = oneCoin;
                continue;
            }
        }
        if (index == 0) {
            return new int[]{balance};
        }
        return Arrays.copyOf(temp, index);
    }
}
