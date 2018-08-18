package ru.inurgalimov.list;

/**
 * @param <T> - generalized parameter
 * @author Nurgalimov Ilshat
 * @version 2.0
 */
public class MyNode<T> {
    private T value;
    private MyNode<T> next;

    public MyNode(T value) {
        this.value = value;
    }

    /**
     * The method implements the Floyd algorithm.
     *
     * @param first
     * @return boolean
     */
    public static boolean hasCycle(MyNode first) {
        MyNode slow = first;
        MyNode fast = first;
        boolean checkResult = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                checkResult = true;
                break;
            }
        }
        return checkResult;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }
}
