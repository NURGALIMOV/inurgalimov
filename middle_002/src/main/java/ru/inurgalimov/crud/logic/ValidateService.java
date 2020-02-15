package ru.inurgalimov.crud.logic;

import ru.inurgalimov.crud.model.User;
import ru.inurgalimov.crud.persistent.DBStore;
import ru.inurgalimov.crud.persistent.Store;

import java.util.Collection;

/**
 * Валидация данных.
 */
public class ValidateService implements Validate {

    /**
     * Единственный экземпляр класса.
     */
    public static final ValidateService INSTANCE = new ValidateService();

    /**
     * Хранилище пользователей.
     */
    private final Store store = DBStore.getInstance();

    /**
     * Конструктор.
     */
    private ValidateService() {

    }

    /**
     * Возвращает единственный экземпляр класса.
     *
     * @return экземпляр класса.
     */
    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        return store.add(user) != null;
    }

    @Override
    public boolean update(User user) {
        return store.update(user);
    }

    @Override
    public boolean delete(User user) {
        return store.delete(user);
    }

    @Override
    public Collection<User> findAll() {
        return store.findAll();
    }

    @Override
    public User findById(User user) {
        return store.findById(user);
    }
}
