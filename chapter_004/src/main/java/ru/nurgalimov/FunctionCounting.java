package ru.nurgalimov;

import java.util.function.*;
import java.util.*;

public class FunctionCounting {
    List<Double> diapason(int start, int end, BiFunction<Double, Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for(int i = start; i <= end; i++) {
            list.add(func.apply(i * 1.0, 1.0));
        }
        return list;
    }
}
