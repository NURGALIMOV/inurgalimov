package ru.inurgalimov.bomberman;

public class Game {
    public static void main(String[] args) {
        Board board = new Board(5);
        Thread player = board.playerThread("Player-1");
        Thread monster = board.monsterThread();
        player.start();
        monster.start();

        try {
            player.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

