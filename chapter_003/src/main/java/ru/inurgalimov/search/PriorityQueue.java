package ru.inurgalimov.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<Task>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставки использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        tasks.add(task);
        List list = tasks.stream().sorted(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getPriority() - o2.getPriority();
            }
        }).collect(Collectors.toList());
        LinkedList<Task> linkedList = new LinkedList<>();
        linkedList.addAll(list);
        tasks = linkedList;
    }

    public Task take() {
        return this.tasks.poll();
    }
}
