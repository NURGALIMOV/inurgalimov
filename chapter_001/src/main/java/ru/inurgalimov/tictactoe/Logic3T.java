package ru.inurgalimov.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        int horizontally = 0;
        int vertically = 0;
        int diagonally1 = 0;
        int diagonally2 = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j].hasMarkX()) {
                    horizontally++;
                }
                if (table[j][i].hasMarkX()) {
                    vertically++;
                }
                if ((i == j) && table[i][j].hasMarkX()) {
                    diagonally1++;
                }
                if (table[i][table.length - 1 - i].hasMarkX()) {
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

    public boolean isWinnerO() {
        int horizontally = 0;
        int vertically = 0;
        int diagonally1 = 0;
        int diagonally2 = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j].hasMarkO()) {
                    horizontally++;
                }
                if (table[j][i].hasMarkO()) {
                    vertically++;
                }
                if ((i == j) && table[i][j].hasMarkO()) {
                    diagonally1++;
                }
                if (table[i][table.length - 1 - i].hasMarkO()) {
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

    public boolean hasGap() {
        for (Figure3T[] figureArray : table) {
            for (Figure3T figure : figureArray) {
                if (!figure.hasMarkO() && !figure.hasMarkX()) {
                    return true;
                }
            }
        }
        return false;
    }
}
