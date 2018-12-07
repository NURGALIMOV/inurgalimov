package ru.inurgalimov.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final int size;
    private final ReentrantLock[][] board;
    private Random random;

    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[this.size][this.size];
        this.random = new Random();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                board[i][j] = new ReentrantLock(true);
            }
        }
        for (int i = 0; i < (int) size / 2; i++) {
            int k = random.nextInt(size - 2) + 1;
            int l = random.nextInt(size - 2) + 1;
            if (k != 0 && l != 0) {
                board[k][l].lock();
            }
        }
    }

    public boolean move(ReentrantLock source, ReentrantLock dist) {
        source.lock();
        boolean result = false;
        try {
            result = dist.tryLock(500, TimeUnit.MILLISECONDS);
            if (!result && Thread.currentThread().getName().equals("Monster")) {
                result = dist.tryLock(4500, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result) {
            source.unlock();
        }
        return result;
    }

    public Thread playerThread(String name) {
        return new Thread(new Player(name, board, this));
    }

    public Thread monsterThread() {
        Thread tempMonster = new Thread(new Monster(board, this));
        tempMonster.setDaemon(true);
        return tempMonster;
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
