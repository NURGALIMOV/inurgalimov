package ru.inurgalimov.sort;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> userList) {
        Set<User> userSet = new TreeSet<>();
        userSet.addAll(userList);

        return userSet;
    }

    public List<User> sortNameLength(List<User> userList) {
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                Integer lengthO1 = o1.getName().length();
                Integer lengthO2 = o2.getName().length();
                return lengthO1.compareTo(lengthO2);
            }
        });
        return userList;
    }

    public List<User> sortByAllFields(List<User> userList) {
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName()) != 0 ? o1.getName().compareTo(o2.getName())
                        : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return userList;
    }
}