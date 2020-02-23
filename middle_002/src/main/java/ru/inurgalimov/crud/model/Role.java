package ru.inurgalimov.crud.model;

/**
 * Роль пользователя.
 */
public enum Role {

    ADMINISTRATOR("Администратор"), USER("Пользователь");

    /** Наименование роли. */
    private String roleName;

    /**
     * Конструктор.
     *
     * @param roleName наименование роли.
     */
    private Role(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Возвращает наименование роли.
     *
     * @return наименование роли.
     */
    public String getRoleName() {
        return roleName;
    }

}
