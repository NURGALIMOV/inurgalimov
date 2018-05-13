package ru.inurgalimov.array;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenCheckingArraySorting() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] result = bubbleSort.sort(new int[]{5, 1, 2, 7, 3});
        int[] expect = new int[]{1, 2, 3, 5, 7};
        assertThat(result, is(expect));
    }
}
