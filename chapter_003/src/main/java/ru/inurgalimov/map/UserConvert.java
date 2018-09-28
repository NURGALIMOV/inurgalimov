package ru.inurgalimov.map;

import java.util.*;
import java.util.stream.Collectors;


public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        return (HashMap<Integer, User>) list.stream().collect(Collectors.toMap(user -> user.getId(), t -> t));
    }
}