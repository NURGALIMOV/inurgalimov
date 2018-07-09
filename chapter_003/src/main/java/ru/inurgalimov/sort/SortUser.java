package ru.inurgalimov.sort;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> userList) {
        Set<User> userSet = new TreeSet<>();
        userSet.addAll(userList);

        return userSet;
    }
}
