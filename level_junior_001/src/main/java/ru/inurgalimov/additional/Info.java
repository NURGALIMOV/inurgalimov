package ru.inurgalimov.additional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        countAdd = (int) current.stream().filter(x -> !pre.containsKey(x.id)).count();
        countRemove = (int) previoues.stream().filter(x -> !cur.containsKey(x.id)).count();
        countMod = (int) previoues.stream().filter(x -> cur.containsKey(x.id) && !cur.get(x.id).equals(x.name)).count();
    }

    private Map<Integer, String> addMap(List<Store.User> list) {
        return list.stream().collect(Collectors.toMap(x -> x.id, u -> u.name));
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
