package ru.nurgalimov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void whenSortAndRemoveExtrasFromTheList() {
        Student student1 = new Student("Илшат", 5);
        Student student2 = new Student("Иван", 2);
        Student student3 = new Student("Вася", 4);
        Student student4 = new Student("Андрей", 5);

        assertThat(Student.levelOf(List.of(student1, student2, student3, student4), 4),
                is(List.of(student1, student4)));
    }
}