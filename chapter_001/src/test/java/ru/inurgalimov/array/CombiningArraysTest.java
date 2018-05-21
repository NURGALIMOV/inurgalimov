package ru.inurgalimov.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CombiningArraysTest {
    @Test
    public void whenCheckingArrayCombining() {
        CombiningArrays combiningArrays = new CombiningArrays();
        int[] a = {2, 5, 6, 7, 8, 9};
        int[] b = {0, 7, 2, 4};
        int[] result = combiningArrays.combiningTwoArrays(a, b);
        int[] expect = new int[]{2, 5, 6, 7, 8, 9, 0, 7, 2, 4};
        assertThat(result, is(expect));
    }
}
