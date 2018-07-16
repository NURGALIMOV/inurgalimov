package ru.inurgalimov.comparator;

import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        char[] leftArray = left.toCharArray();
        char[] rightArray = right.toCharArray();
        int count = rightArray.length;
        if (leftArray.length >= rightArray.length) {
            count = leftArray.length;
        }
        int temp;
        try {
            for (int i = 0; i < count; i++) {
                temp = Character.compare(leftArray[i], rightArray[i]);
                if (temp != 0) {
                    result = temp;
                    break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            result = 1;
            if (rightArray.length > leftArray.length) {
                result = -1;
            }
        }

        return result;
    }
}