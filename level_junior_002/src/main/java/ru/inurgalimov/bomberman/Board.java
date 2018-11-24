package ru.inurgalimov.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final int size;
    private final ReentrantLock[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        new Thread(() -> {
            Thread.currentThread().setName("Player 2");
            int i = this.size - 1;
            int j = this.size - 1;
            int[][][] threadArr2 = {{{-1, -1}, {-1, 0}, {-1, 1}},
                    {{0, -1}, null, {0, 1}},
                    {{1, -1}, {1, 0}, {1, 1}}};
            while (true) {
                boolean condition = true;
                int k = i;
                int l = j;
                int[] t1;
                while (condition) {
                    k = i;
                    l = j;
                    t1 = this.nextStep(threadArr2, k, l);
                    k = k + t1[0];
                    l = l + t1[1];
                    condition = !this.move(board[i][j], board[k][l]);
                }
                System.out.println(Thread.currentThread().getName() + " перешел на ячейку [" + k + "][" + l + "].");
                i = k;
                j = l;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int[][][] temp = {{{-1, -1}, {-1, 0}, {-1, 1}},
                        {{0, -1}, null, {0, 1}},
                        {{1, -1}, {1, 0}, {1, 1}}};
                threadArr2 = temp;
            }
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Player 1");
            int i = 0;
            int j = 0;
            int[][][] threadArr1 = {{{-1, -1}, {-1, 0}, {-1, 1}},
                    {{0, -1}, null, {0, 1}},
                    {{1, -1}, {1, 0}, {1, 1}}};
            while (true) {
                boolean condition = true;
                int k = i;
                int l = j;
                int[] t1;
                while (condition) {
                    k = i;
                    l = j;
                    t1 = this.nextStep(threadArr1, k, l);
                    k = k + t1[0];
                    l = l + t1[1];
                    condition = !this.move(board[i][j], board[k][l]);
                }
                System.out.println(Thread.currentThread().getName() + " перешел на ячейку [" + k + "][" + l + "].");
                i = k;
                j = l;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int[][][] temp = {{{-1, -1}, {-1, 0}, {-1, 1}},
                        {{0, -1}, null, {0, 1}},
                        {{1, -1}, {1, 0}, {1, 1}}};
                threadArr1 = temp;
            }
        }).start();
    }

    public boolean move(ReentrantLock source, ReentrantLock dist) {
        source.lock();
        boolean result = false;
        try {
            result = dist.tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result) {
            source.unlock();
        }
        return result;
    }

    public int[] nextStep(int[][][] temp, int k, int l) {
        int[] result = null;
        boolean check = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (temp[i][j] != null && k + temp[i][j][0] >= 0 && k + temp[i][j][0] < size
                        && l + temp[i][j][1] >= 0 && l + temp[i][j][1] < size) {
                    result = temp[i][j];
                    temp[i][j] = null;
                    check = true;
                    break;
                }
                temp[i][j] = null;
            }
            if (check) {
                break;
            }
        }
        return result;
    }
}
