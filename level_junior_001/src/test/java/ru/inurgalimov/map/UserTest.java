package ru.inurgalimov.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class UserTest {
    @Test
    public void whenNotOverlappedEqualsHashCode() {
        Calendar calendar = new GregorianCalendar(1986, 3, 26);
        String name = "Ilshat";
        int children = 1;
        User one = new User(name, children, calendar);
        User two = new User(name, children, calendar);

        Map<User, Object> testMap = new HashMap<>();
        testMap.put(one, 1);
        testMap.put(two, 1);
        System.out.println(testMap);
    }
}