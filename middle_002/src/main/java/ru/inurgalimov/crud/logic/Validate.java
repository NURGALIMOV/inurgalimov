package ru.inurgalimov.crud.logic;

import ru.inurgalimov.crud.model.User;

import java.util.Collection;

/**
 * Интерфейс валидации данных.
 */
public interface Validate {
    /**
     * Добавление пользователя.
     *
     * @param user добавляемый пользователь.
     * @return результат добавления пользователя.
     */
    boolean add(User user);

    /**
     * Обновление данных пользователя.
     *
     * @param user источник обновляемых данных.
     * @return результат обновления данных.
     */
    boolean update(User user);

    /**
     * Удалеине пользователя.
     *
     * @param user удаляемый пользователь.
     * @return результат удаления пользователя.
     */
    boolean delete(User user);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список всех пользователей.
     */
    Collection<User> findAll();

    /**
     * Поиск пользователя.
     *
     * @param user источник данных для поиска.
     * @return возвращает найденного пользователя.
     */
    User findById(User user);
}
