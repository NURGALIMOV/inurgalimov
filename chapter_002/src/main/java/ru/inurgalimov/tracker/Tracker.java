package ru.inurgalimov.tracker;

import ru.inurgalimov.models.*;

import java.util.Random;

public class Tracker {
    private final Item[] items = new Item[100];
    private static final Random RN = new Random();
    private int position = 0;

    public Item add(Item item) {
        item.setId(this.generateId(item.getCreate()));
        this.items[this.position++] = item;
        return item;
    }

    public void replace(String id, Item item) {
        for (int i = 0; i <= position; i++) {
            if (items[i].getId().equals(id)) {
                items[i] = item;
            }
        }
    }

    public void delete(String id) {
        int index = 0;
        while (!items[index].getId().equals(id)) {
            index++;
        }
        System.arraycopy(items, index + 1, items, index, position - index);
    }

    public Item[] findAll() {
        Item it[] = new Item[position + 1];
        System.arraycopy(items, 0, it, 0, position + 1);
        return it;
    }

    public Item[] findByName(String key) {
        Item temp[] = new Item[items.length];
        int count = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equals(key)) {
                temp[count] = items[i];
                count++;
            }
        }
        Item it[] = new Item[count];
        for (int i = 0; i < count; i++) {
            it[i] = temp[i];
        }
        return it;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item it : items) {
            if(it.getId().equals(id)) {
                result = it;
            }
        }
        return result;
    }

    private String generateId(long create) {
        return String.valueOf(RN.nextLong() + create);
    }
}
