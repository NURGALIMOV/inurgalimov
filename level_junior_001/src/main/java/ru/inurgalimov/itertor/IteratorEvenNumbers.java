package ru.inurgalimov.itertor;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorEvenNumbers implements Iterator {
    private final int[] numbers;
    private int index = 0;

    public IteratorEvenNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (; index < numbers.length; index++) {
            if (numbers[index] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        Integer value = 0;
        if (hasNext()) {
            value = numbers[index++];
        } else if (index >= numbers.length) {
            throw new NoSuchElementException();
        }
        return value;
    }
}
