package ru.inurgalimov.generic;

public class UserStore implements Store<User>{

    @Override
    public void add(User model) {

    }

    @Override
    public boolean replace(String id, User model) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
