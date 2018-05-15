package ru.inurgalimov.array;

import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i].equals(array[j])) {
                        array[j] = null;
                    }
                }
            }
        }
        int arrayLength = array.length - 1;
        for (int i = 0; i <= arrayLength; i++) {
            if (array[i] == null) {
                if (array[arrayLength] != null) {
                    String temp = array[arrayLength];
                    array[arrayLength] = array[i];
                    array[i] = temp;
                } else {
                    arrayLength--;
                    i--;
                }
            }
        }
        return Arrays.copyOf(array, arrayLength + 1);
    }
}
