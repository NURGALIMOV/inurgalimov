package ru.inurgalimov.xo;

import java.util.*;

public class User implements Player {

    private final String symbol;
    private final Field field;
    private static final Scanner sc = new Scanner(System.in);
    private int x;
    private int y;

    public User(final String symbol, final Field field) {
        this.symbol = symbol;
        this.field = field;
    }
    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean move() {
        int[] move = input();
        return field.place(move[1], move[0], this);
    }

    @Override
    public int[] input() {
        int[] result = new int[2];
        System.out.println("Введите следующий шаг:");
        result[0] = sc.nextInt();
        result[1] = sc.nextInt();
        return result;
    }
}
