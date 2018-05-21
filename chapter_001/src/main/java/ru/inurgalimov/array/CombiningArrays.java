package ru.inurgalimov.array;

public class CombiningArrays {

    public int[] combiningTwoArrays(int[] a, int[] b) {
        int[] array = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            array[i] = a[i];
        }
        for (int i = a.length, j = 0; i < array.length; i++, j++) {
            array[i] = b[j];
        }
        return array;
    }
}
