package ru.inurgalimov.additional;

import java.util.List;

public class Info {
    private final List previoues;
    private final List current;
    private int countAdd;
    private int countRemove;
    private int countMod;

    public Info(List<Store.User> previoues, List<Store.User> current) {
        this.previoues = previoues;
        this.current = current;
        countAdd = 0;
        countMod = 0;
        countRemove = 0;
    }

    public void countingStatistics() {
        int i = current.size() - previoues.size();
        countAdd = i > 0 ? i : 0;
        countRemove = i > 0 ? 0 : Math.abs(i);
        for (Object user : previoues) {
            Store.User u = (Store.User) user;
            if (current.contains(u)) {
                Store.User t = (Store.User) current.get(current.indexOf(u));
                if (!u.name.equals(t.name)) {
                    countMod++;
                }
            }
        }
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
