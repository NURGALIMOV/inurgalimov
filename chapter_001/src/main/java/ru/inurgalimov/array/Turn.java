package ru.inurgalimov.array;

public class Turn {
    public int[] back(int[] array) {
        int temp;
        for (int i = 0, j = array.length - 1; i < array.length / 2; i++, j--) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
}