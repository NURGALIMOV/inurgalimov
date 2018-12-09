package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

import java.util.List;
import java.util.function.BiFunction;

public class TrackerVersionOne {
    private Tracker tracker;
    private static TrackerVersionOne trackerVersionOne;

    private TrackerVersionOne(Tracker tracker) {
        this.tracker = tracker;
    }

    public static TrackerVersionOne getTracker() {
        if (trackerVersionOne == null) {
            trackerVersionOne = new TrackerVersionOne(new Tracker());
        }
        return trackerVersionOne;
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
