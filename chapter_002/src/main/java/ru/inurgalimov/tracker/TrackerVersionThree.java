package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

import java.util.List;
import java.util.function.BiFunction;

public class TrackerVersionThree {
    private ITracker tracker;

    private TrackerVersionThree(ITracker tracker) {
        this.tracker = tracker;
    }

    public static TrackerVersionThree getTracker() {
        return Holder.TRACKER_VERSION_THREE;
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

    private static final class Holder {
        private static final TrackerVersionThree TRACKER_VERSION_THREE = new TrackerVersionThree(new Tracker());
    }
}
