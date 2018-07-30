package ru.inurgalimov.itertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTwoDimensionalArray implements Iterator {
    private final Integer[] array;
    private int index = 0;

    public IteratorTwoDimensionalArray(int[][] array) {
        List<Integer> tempList = new ArrayList<>();
        for (int[] tempArray : array) {
            for (int tempValue : tempArray) {
                tempList.add(tempValue);
            }
        }
        this.array = tempList.toArray(new Integer[tempList.size()]);
    }
    @Override
    public boolean hasNext() {
        return array.length > index;
    }

    @Override
    public Object next() {
        return array[index++];
    }
}
