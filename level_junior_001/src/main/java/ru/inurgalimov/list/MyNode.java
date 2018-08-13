package ru.inurgalimov.list;

/**
 * @param <T> - generalized parameter
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class MyNode<T> {
    private T value;
    private MyNode<T> next;

    public MyNode(T value) {
        this.value = value;
    }

    public static boolean hasCycle(MyNode first) {
        MyNode check = first;
        boolean checkResult = false;
        while (check.next != null) {
            if (check.next == first) {
                checkResult = true;
                break;
            }
            check = check.next;
        }
        return checkResult;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }
}
