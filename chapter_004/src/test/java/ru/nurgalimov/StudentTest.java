package ru.nurgalimov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void whenSortAndRemoveExtrasFromTheList() {
        List<Student> testList = new ArrayList();
        Student student1 = new Student("Илшат", 5);
        Student student2 = new Student("Иван", 2);
        Student student3 = new Student("Вася", 4);
        Student student4 = new Student("Андрей", 5);
        testList.add(student1);
        testList.add(student2);
        testList.add(student3);
        testList.add(student4);
        testList.add(null);
        List<Student> checkList = new ArrayList<>();
        checkList.add(student1);
        checkList.add(student4);
        assertThat(Student.levelOf(testList, 4), is(checkList));
    }
}