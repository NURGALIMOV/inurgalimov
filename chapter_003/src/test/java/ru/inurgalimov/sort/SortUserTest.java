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

    @Test
    public void whenListSortLengthName() {
        List<User> userList = new ArrayList<>();
        User user1 = new User("Dcb", 15);
        User user2 = new User("Bf", 90);
        User user3 = new User("Iklm", 20);
        User user4 = new User("L", 9);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        List<User> result = new SortUser().sortNameLength(userList);
        assertThat(result.get(0), is(user4));
        assertThat(result.get(3), is(user3));
    }

    @Test
    public void whenListSortLengthNameAndAge() {
        List<User> userList = new ArrayList<>();
        User user1 = new User("D", 15);
        User user2 = new User("B", 12);
        User user3 = new User("B", 20);
        User user4 = new User("A", 9);

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        List<User> result = new SortUser().sortByAllFields(userList);
        assertThat(result.get(0), is(user4));
        assertThat(result.get(2), is(user3));
    }
}
