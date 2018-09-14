package ru.nurgalimov;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FunctionCountingTest {
    @Test
    public void whenLine() {
        FunctionCounting functionCounting = new FunctionCounting();
        List<Double> resultList = functionCounting.diapason(1, 5, (a, b) -> a + b);
        List<Double> testList = new ArrayList<>();
        testList.add(2.0);
        testList.add(3.0);
        testList.add(4.0);
        testList.add(5.0);
        testList.add(6.0);
        assertThat(resultList, is(testList));
    }

    @Test
    public void whenQuadratic() {
        FunctionCounting functionCounting = new FunctionCounting();
        List<Double> resultList = functionCounting.diapason(1, 5, (a, b) -> a * a + b);
        List<Double> testList = new ArrayList<>();
        testList.add(2.0);
        testList.add(5.0);
        testList.add(10.0);
        testList.add(17.0);
        testList.add(26.0);

        assertThat(resultList, is(testList));
    }

    @Test
    public void whenLogarithmic() {
        FunctionCounting functionCounting = new FunctionCounting();
        List<Double> resultList = functionCounting.diapason(1, 5, (a, b) -> Math.log(a) + b);
        List<Double> testList = new ArrayList<>();
        testList.add(Math.log(1.0) + 1.0);
        testList.add(Math.log(2.0) + 1.0);
        testList.add(Math.log(3.0) + 1.0);
        testList.add(Math.log(4.0) + 1.0);
        testList.add(Math.log(5.0) + 1.0);
        assertThat(resultList, is(testList));
    }
}