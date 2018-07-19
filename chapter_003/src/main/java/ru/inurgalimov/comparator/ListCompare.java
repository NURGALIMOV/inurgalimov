package ru.inurgalimov.comparator;

import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        char[] leftArray = left.toCharArray();
        char[] rightArray = right.toCharArray();
        int count = 0;
        if (leftArray.length >= rightArray.length) {
            count = rightArray.length;
        } else {
            count = leftArray.length;
        }
        int temp;
        for (int i = 0; i < count; i++) {
            temp = Character.compare(leftArray[i], rightArray[i]);
            if (temp != 0) {
                result = temp;
                break;
            }
        }
        if (leftArray.length != rightArray.length && result == 0) {
            if (rightArray.length > leftArray.length) {
                result = -1;
            } else {
                result = 1;
            }
        }
        return result;
    }
}