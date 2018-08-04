package ru.inurgalimov.itertor;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTwoDimensionalArray implements Iterator {
    private int line;
    private int cell;
    private final int[][] array;


    public IteratorTwoDimensionalArray(int[][] array) {
        this.array = array;
        line = 0;
        cell = 0;
    }

    @Override
    public boolean hasNext() {
        return !((line == array.length - 1) && (cell >= array[line].length));
    }

    @Override
    public Object next() {
        Integer result;
        if (!(line < array.length)) {
            throw new NoSuchElementException();
        }
        if (!(cell < array[line].length)) {
            cell = 0;
            ++line;
        }
        result = array[line][cell++];
        return result;
    }
}
