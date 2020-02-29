package ru.inurgalimov.crud.logic;

import ru.inurgalimov.crud.model.User;
import ru.inurgalimov.crud.persistent.MemoryStore;
import ru.inurgalimov.crud.persistent.Store;

import java.util.Collection;
import java.util.Objects;

/**
 * Сервис для тестов.
 */
public class ValidateServiceStub implements Validate {
    /**
     * Единственный экземпляр класса.
     */
    public static final ValidateServiceStub INSTANCE = new ValidateServiceStub();

    /**
     * Хранилище пользователей.
     */
    private final Store store = MemoryStore.getInstance();

    /**
     * Конструктор.
     */
    private ValidateServiceStub() {

    }

    /**
     * Возвращает единственный экземпляр класса.
     *
     * @return экземпляр класса.
     */
    public static Validate getInstance() {
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

    @Override
    public boolean isCredentional(String login, String password) {
        return (!Objects.isNull(login) && !Objects.isNull(password)) && findAll().stream()
                .anyMatch(user -> ((login.equals(user.getLogin())) && (password.equals(user.getPassword()))));
    }
}
