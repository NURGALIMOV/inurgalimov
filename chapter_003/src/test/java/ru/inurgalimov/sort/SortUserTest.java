package ru.inurgalimov.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenListSortTreeSet() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("D", 15));
        userList.add(new User("B", 12));
        userList.add(new User("I", 20));
        userList.add(new User("A", 9));

        TreeSet<User> result = (TreeSet<User>) new SortUser().sort(userList);
        assertThat(result.first(), is(userList.get(3)));
        assertThat(result.last(), is(userList.get(2)));
    }
}
