package ru.inurgalimov.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        return Arrays.stream(array).flatMapToInt(x -> Arrays.stream(x))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
