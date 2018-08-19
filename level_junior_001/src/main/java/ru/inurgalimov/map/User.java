package ru.inurgalimov.map;

import java.util.Calendar;

/**
 * My model User.
 *
 * @author Nurgalimov Ilshat
 */

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + children + this.birthday.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return this.name.equals(user.name) && this.children == user.children && this.birthday.equals(user.birthday);
    }
}
