package ru.inurgalimov.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConvertList2Array {
    private int i = 0;
    private int j = 0;

    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil(list.size() * 1.0 / rows);
        int[][] array = new int[rows][cells];
        list.stream().forEach(x -> {
            array[i][j++] = x;
            if (j == cells) {
                i++;
                j = 0;
            }
        });
        i = 0;
        j = 0;
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(x -> Arrays.stream(x))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}