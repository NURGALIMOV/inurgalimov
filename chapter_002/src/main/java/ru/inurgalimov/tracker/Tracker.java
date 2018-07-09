package ru.inurgalimov.tracker;

import ru.inurgalimov.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
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

    public Item[] findAll() {
        return items.toArray(new Item[items.size()]);
    }

    public Item[] findByName(String key) {
        List<Item> temp = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                temp.add(item);
            }
        }
        return temp.toArray(new Item[temp.size()]);
    }

    public Item findById(String id) {
        Item result = null;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                result = items.get(i);
            }
        }
        return result;
    }

    private String generateId(long create) {
        return String.valueOf(RN.nextLong() + create);
    }
}
