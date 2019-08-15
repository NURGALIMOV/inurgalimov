package ru.inurgalimov.cache;

/**
 * Кеш.
 */
public interface ICache {

    /** Получение данных из кеша */
    <T> T get(String key);

    /** Запись данных в кеш */
    <K> void set(K k);
}
