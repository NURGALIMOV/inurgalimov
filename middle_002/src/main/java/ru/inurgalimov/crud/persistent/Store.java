package ru.inurgalimov.crud.persistent;

import ru.inurgalimov.crud.model.User;

import java.util.Collection;

/**
 * Интерфейс для хранилищ пользователей.
 */
public interface Store {

    /**
     * Добавляет нового пользователя в хранилище.
     *
     * @param user новый пользователь.
     */
    User add(User user);

    /**
     * Обновление информации о существующем пользователе.
     *
     * @param user источник обновляемых данных.
     */
    boolean update(User user);

    /**
     * Удаление пользователя.
     *
     * @param user информация для удаления.
     */
    boolean delete(User user);

    /**
     * Возвращает список всех пользователей.
     */
    Collection<User> findAll();

    /**
     * Поиск пользователя по ID.
     *
     * @param user источник искомого ID.
     */
    User findById(User user);
}
