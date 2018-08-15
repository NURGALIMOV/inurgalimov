package ru.inurgalimov.list;

/**
 * @param <T> - generalized parameter
 * @author Nurgalimov Ilshat
 * @version 1.1
 */
public class MyNode<T> {
    private T value;
    private MyNode<T> next;

    public MyNode(T value) {
        this.value = value;
    }

    public static boolean hasCycle(MyNode first) {
        MyNode check = first;
        int count = 1;
        boolean checkResult = false;
        while (check.next != null) {
            MyNode tempNode = first;
            for (int i = count; i > 0; i--) {
                if (check.next == tempNode) {
                    checkResult = true;
                    break;
                }
                tempNode = tempNode.next;
            }
            if (checkResult) {
                break;
            }
            check = check.next;
            count++;
        }
        return checkResult;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }
}
