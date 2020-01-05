package ru.inurgalimov.crud.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Модель данных для CRUD.
 */
public class User implements Serializable {

    /** ID пользователя */
    private UUID id;
    
    /** Имя пользователя */
    private String name;

    /** Логин пользователя */
    private String login;

    /** Почта пользователя */
    private String email;

    /** Дата создания пользователя */
    private Date createDate;

    /**
     * Конструктор.
     *
     * @param aName имя пользователя.
     * @param aLogin логин пользователя.
     * @param aEmail почта пользователя.
     * @param aID ID пользователя.
     */
    public User(String aName, String aLogin, String aEmail, UUID aID) {
        id = (aID != null) ? aID : UUID.randomUUID();
        name = aName;
        login = aLogin;
        email = aEmail;
        createDate = new Date();
    }

    /**
     * Конструктор.
     *
     * @param aName имя пользователя.
     * @param aLogin логин пользователя.
     * @param aEmail почта пользователя.
     */
    public User(String aName, String aLogin, String aEmail) {
        this(aName, aLogin, aEmail, null);
    }

    /**
     * Возвращает ID пользователя.
     *
     * @return ID пользователя.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя пользователя.
     *
     * @param name имя пользователя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает логин пользователя.
     *
     * @return логин пользователя.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Устанавливает логин пользователя.
     *
     * @param login логин пользователя.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Возвращает почту пользователя.
     *
     * @return почта пользователя.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает почту пользователя.
     *
     * @param email почта пользователя.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает дату создания пользователя.
     *
     * @return дата создания пользователя.
     */
    public Date getCreateDate() {
        return createDate;
    }
}
