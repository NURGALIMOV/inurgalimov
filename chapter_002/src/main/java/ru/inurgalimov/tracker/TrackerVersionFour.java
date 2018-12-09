package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

import java.util.List;
import java.util.function.BiFunction;

public enum TrackerVersionFour {
    TRACKER_VERSION_FOUR;
    private Tracker tracker;

    private TrackerVersionFour() {
        tracker = new Tracker();
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public void replace(String id, Item item) {
        tracker.replace(id, item);
    }

    public void delete(String id) {
        tracker.delete(id);
    }

    public <T> T find(String k, BiFunction<List, String, T> biFunction) {
        return tracker.find(k, biFunction);
    }

    public Item findById(String id) {
        return tracker.findById(id);
    }
}
