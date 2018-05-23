package ru.inurgalimov.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        int arrayLength = array.length;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[arrayLength - 1];
                    arrayLength--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, arrayLength);
    }
}