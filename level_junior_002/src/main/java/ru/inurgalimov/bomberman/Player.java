package ru.inurgalimov.bomberman;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Player implements Runnable {
    private final String playerName;
    private ReentrantLock[][] boardArr;
    private Board board;

    public Player(String playerName, ReentrantLock[][] boardArr, Board board) {
        this.playerName = playerName;
        this.board = board;
        this.boardArr = boardArr;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(playerName);
        int i = 0;
        int j = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            boolean condition = true;
            if (boardArr[i][j].hasQueuedThreads()) {
                System.out.println("Game over!!!");
                break;
            }
            int k = 0;
            int l = 0;
            while (condition) {
                System.out.println("Ход игрока " + Thread.currentThread().getName() + ":");
                k = sc.nextInt();
                l = sc.nextInt();
                if ((Math.abs(i - k) == 0 || Math.abs(i - k) == 1)
                        && (Math.abs(j - l) == 0 || Math.abs(j - l) == 1) && k < boardArr.length && l <= k) {
                    condition = !board.move(boardArr[i][j], boardArr[k][l]);
                }
            }
            System.out.println(Thread.currentThread().getName() + " перешел на ячейку [" + k + "][" + l + "].");
            i = k;
            j = l;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sc.close();
    }
}
