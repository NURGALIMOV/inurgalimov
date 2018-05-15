package ru.inurgalimov.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        //напишите здесь тест, проверяющий удаление дубликатов строк из массива строк.
        ArrayDuplicate arr = new ArrayDuplicate();
        String[] resultArray = arr.remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"});
        String[] expectArray = new String[]{"Привет", "Мир", "Супер"};
        assertThat(resultArray, is(expectArray));
    }
}