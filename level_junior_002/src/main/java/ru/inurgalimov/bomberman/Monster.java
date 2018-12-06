package ru.inurgalimov.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class Monster implements Runnable {
    private ReentrantLock[][] boardArr;
    private Board board;

    public Monster(ReentrantLock[][] boardArr, Board board) {
        this.board = board;
        this.boardArr = boardArr;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Monster");
        int i = boardArr.length - 1;
        int j = boardArr.length - 1;
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
                t1 = board.nextStep(threadArr2, k, l);
                k = k + t1[0];
                l = l + t1[1];
                condition = !board.move(boardArr[i][j], boardArr[k][l]);
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
    }
}
