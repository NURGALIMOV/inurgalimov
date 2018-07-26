package ru.inurgalimov.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortingDepartmentTest {
    String[] testArray = new String[]{"K2\\SK1\\SSK1", "K1\\SK1", "K1", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
            "K2", "K1\\SK2", "K2\\SK1\\SSK2"};

    @Test
    public void whenSortAscending() {
        String[] checkArray = new String[]{"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        SortingDepartment.sortAscending(testArray);
        assertThat(testArray, is(checkArray));
    }

    @Test
    public void whenSortDescending() {
        String[] checkArray = new String[]{"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1",
                "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        SortingDepartment.sortDescending(testArray);
        assertThat(testArray, is(checkArray));
    }
}
