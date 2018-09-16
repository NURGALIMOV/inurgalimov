package ru.inurgalimov.additional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Info {
    private final List<Store.User> previoues;
    private final List<Store.User> current;
    private Map<Integer, String> pre;
    private Map<Integer, String> cur;
    private int countAdd;
    private int countRemove;
    private int countMod;

    public Info(List<Store.User> previoues, List<Store.User> current) {
        this.previoues = previoues;
        this.current = current;
        countAdd = 0;
        countMod = 0;
        countRemove = 0;
        pre = addMap(previoues);
        cur = addMap(current);
    }

    public void countingStatistics() {
        for (Store.User u : current) {
            if (!pre.containsKey(u.id)) {
                countAdd++;
            }
        }
        for (Store.User u : previoues) {
            if (!cur.containsKey(u.id)) {
                countRemove++;
            } else if (cur.containsKey(u.id) && !cur.get(u.id).equals(u.name)) {
                countMod++;
            }
        }
    }

    private Map<Integer, String> addMap(List<Store.User> list) {
        Map<Integer, String> tempMap = new HashMap<>();
        for (Store.User u : list) {
            tempMap.put(u.id, u.name);
        }
        return tempMap;
    }

    public int getCountAdd() {
        return countAdd;
    }

    public int getCountRemove() {
        return countRemove;
    }

    public int getCountMod() {
        return countMod;
    }

}
