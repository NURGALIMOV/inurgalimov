package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

import java.util.List;
import java.util.function.BiFunction;

public class TrackerVersionTwo {
    private ITracker tracker;
    private static final TrackerVersionTwo TRACKER_VERSION_TWO = new TrackerVersionTwo(new Tracker());

    private TrackerVersionTwo(ITracker tracker) {
        this.tracker = tracker;
    }

    public static TrackerVersionTwo getTracker() {
        return TRACKER_VERSION_TWO;
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
