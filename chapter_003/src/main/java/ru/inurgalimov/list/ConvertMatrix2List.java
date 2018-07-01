package ru.inurgalimov.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arr : array) {
            for (int cell : arr) {
                list.add(cell);
            }
        }
        return list;
    }
}
