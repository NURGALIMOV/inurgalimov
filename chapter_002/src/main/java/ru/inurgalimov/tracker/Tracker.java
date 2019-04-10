package ru.inurgalimov.tracker;

import ru.inurgalimov.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;

public class Tracker implements ITracker {
    private final List<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    public Item add(Item item) {
        item.setId(this.generateId(item.getCreate()));
        items.add(item);
        return item;
    }

    public void replace(String id, Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.set(i, item);
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
            }
        }
    }

    @Override
    public Item[] findAll() {
        return new Item[0];
    }

    @Override
    public Item[] findByName(String key) {
        return new Item[0];
    }

    public <T> T find(String k, BiFunction<List, String, T> biFunction) {
        return biFunction.apply(items, k);
    }

    public Item findById(String id) {
        return find(id, (a, b) -> {
            Item result = null;
            Item it;
            for (int i = 0; i < a.size(); i++) {
                it = (Item) a.get(i);
                if (it.getId().equals(id)) {
                    result = it;
                    break;
                }
            }
            return result;
        });
    }

    private String generateId(long create) {
        return String.valueOf(RN.nextLong() + create);
    }
}
