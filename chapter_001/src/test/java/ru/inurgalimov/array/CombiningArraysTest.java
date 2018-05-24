package ru.inurgalimov.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CombiningArraysTest {
    @Test
    public void whenCheckingArrayCombining() {
        CombiningArrays combiningArrays = new CombiningArrays();
        int[] a = {0, 3};
        int[] b = {1, 4};
        int[] result = combiningArrays.combiningTwoArrays(a, b);
        int[] expect = new int[]{0, 1, 3, 4};
        assertThat(result, is(expect));
    }
}
