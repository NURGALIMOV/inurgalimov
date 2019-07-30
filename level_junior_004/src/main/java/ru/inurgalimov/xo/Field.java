package ru.inurgalimov.xo;

public class Field {

    private static final String X = "X";
    private static final String O = "O";
    private static final String EMPTY = "*";
    private static final int DEFAULT_SIZE = 3;
    private static final String LN = System.getProperty("line.separator");

    private int size;
    private String[][] field;

    public Field() {
        this(DEFAULT_SIZE);
    }

    public Field(final int size) {
        this.size = size;
        field = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = EMPTY;
            }
        }
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");

        for (int i = 1; i <= size; i++) {
            sb.append(String.format("%s ", i));
        }
        sb.append(LN);

        for (int i = 0; i < size; i++) {
            sb.append(String.format("%s ", i + 1));
            for (int j = 0; j < size; j++) {
                sb.append(String.format("%s ", field[i][j]));
            }
            sb.append(LN);
        }
        System.out.println(sb.toString());
    }

    public boolean place(int x, int y, Player player) {
        boolean result = EMPTY.equals(field[y][x]);
        if (result) {
            field[y][x] = player.getSymbol();
        }
        return result;
    }
}
