package ru.inurgalimov.itertor;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class IteratorTwoDimensionalArrayTest {
    @Test
    public void whenWeTraverseTheArrayWithAnIterator() {
        IteratorTwoDimensionalArray testIterator = new IteratorTwoDimensionalArray(new int[][]{{1, 2}, {3, 4}});
        testIterator.next();
        testIterator.next();
        int result = (Integer) testIterator.next();

        assertThat(result, is(3));
    }

    @Test
    public void whenWeCheckThePossibilityOfTheNextStep() {
        IteratorTwoDimensionalArray testIterator = new IteratorTwoDimensionalArray(new int[][]{{1, 2}, {3, 4}});
        testIterator.next();
        testIterator.next();
        boolean result = testIterator.hasNext();

        assertThat(result, is(true));

        testIterator.next();
        testIterator.next();
        result = testIterator.hasNext();
        assertThat(result, is(false));
    }

}