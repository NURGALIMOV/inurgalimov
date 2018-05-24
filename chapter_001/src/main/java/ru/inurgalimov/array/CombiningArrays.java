package ru.inurgalimov.array;

public class CombiningArrays {

    public int[] combiningTwoArrays(int[] a, int[] b) {
        int[] array = new int[a.length + b.length];
        for (int i = 0, j = 0, k = 0; k < array.length; k++) {
            if (i == a.length) {
                array[k] = b[j];
            } else if (j == b.length) {
                array[k] = a[i];
            } else if (a[i] < b[j]) {
                array[k] = a[i];
                i++;
            } else {
                array[k] = b[j];
                j++;
            }
        }
        return array;
    }
}
