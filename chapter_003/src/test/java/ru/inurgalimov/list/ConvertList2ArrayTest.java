package ru.inurgalimov.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void whenConvertList() {
        ConvertList2Array convertList2Array = new ConvertList2Array();
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {1, 3, 5});
        list.add(new int[] {0, 2, 4});

        List<Integer> result = convertList2Array.convert(list);
        List<Integer> expect = new ArrayList();
        expect.add(1);
        expect.add(3);
        expect.add(5);
        expect.add(0);
        expect.add(2);
        expect.add(4);
        assertThat(result, is(expect));
    }
}
