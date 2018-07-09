package ru.inurgalimov.sort;

public class User implements Comparable<User> {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User o) {
        return this.getAge().compareTo(o.getAge());
    }
}
