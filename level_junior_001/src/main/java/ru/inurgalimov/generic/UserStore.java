package ru.inurgalimov.generic;

/**
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class UserStore extends AbstractStore<User> {

    public UserStore(int size) {
        this.simpleArray = new SimpleArray<User>(size);
        this.size = size;
    }
}
