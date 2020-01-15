package ru.inurgalimov.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table.clone();
    }

    public boolean isWinnerX() {
        return this.isWinner('X');
    }

    public boolean isWinnerO() {
        return this.isWinner('O');
    }

    public boolean hasGap() {
        for (Figure3T[] figureArray : table) {
            for (Figure3T figure : figureArray) {
                if (!figure.hasMark('O') && !figure.hasMark('X')) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWinner(char ch) {
        int horizontally = 0;
        int vertically = 0;
        int diagonally1 = 0;
        int diagonally2 = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j].hasMark(ch)) {
                    horizontally++;
                }
                if (table[j][i].hasMark(ch)) {
                    vertically++;
                }
                if ((i == j) && table[i][j].hasMark(ch)) {
                    diagonally1++;
                }
                if (table[i][table.length - 1 - i].hasMark(ch)) {
                    diagonally2++;
                }
            }
            if ((horizontally == table.length) || (vertically == table.length)
                    || (diagonally1 == table.length) || (diagonally2 == table.length)) {
                return true;
            } else {
                horizontally = 0;
                vertically = 0;
            }
        }
        return false;
    }
}
