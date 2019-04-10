package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

import java.util.List;
import java.util.function.BiFunction;

public interface ITracker {
    Item add(Item item);

    void replace(String id, Item item);

    void delete(String id);

    Item[] findAll();

    Item[] findByName(String key);

    Item findById(String id);
    public <T> T find(String k, BiFunction<List, String, T> biFunction);
}
