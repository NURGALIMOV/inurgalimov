package ru.inurgalimov.generic;

/**
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int size) {
        this.simpleArray = new SimpleArray<Role>(size);
        this.size = size;
    }
}
