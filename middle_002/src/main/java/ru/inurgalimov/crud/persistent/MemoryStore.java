package ru.inurgalimov.crud.persistent;

import ru.inurgalimov.crud.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Хранилище пользователей.
 */
public class MemoryStore implements Store {

    /** Единственный экземпляр класса. */
    public static final MemoryStore INSTANCE = new MemoryStore();

    /** Хранилище пользователей. */
    private final ConcurrentMap<UUID, User> storage = new ConcurrentHashMap<>();

    /**
     * Конструктор.
     */
    private MemoryStore() {

    }

    /**
     * Возвращает единственный экземпляр класса.
     *
     * @return экземпляр класса.
     */
    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        return storage.put(user.getId(), user);
    }

    @Override
    public boolean update(User user) {
        User old = storage.get(user.getId());
        if (old != null) {
            String temp = user.getName();
            if ((temp != null) && !old.getName().equals(temp)) {
                old.setName(temp);
            }
            temp = user.getLogin();
            if ((temp != null) && !old.getLogin().equals(temp)) {
                old.setLogin(temp);
            }
            temp = user.getEmail();
            if ((temp != null) && !old.getEmail().equals(temp)) {
                old.setEmail(temp);
            }
            storage.replace(user.getId(), old);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return storage.remove(user.getId()) != null;
    }

    @Override
    public Collection<User> findAll() {
        return storage.values();
    }

    @Override
    public User findById(User user) {
        return storage.get(user.getId());
    }
}
