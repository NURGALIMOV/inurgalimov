package ru.inurgalimov.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil(list.size() * 1.0 / rows);
        int[][] array = new int[rows][cells];
        for (int i = 0, l = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++, l++) {
                if (l >= list.size()) {
                    break;
                }
                array[i][j] = list.get(l);
            }
        }
        return array;
    }
}
