package ru.inurgalimov.itertor;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Test task "5.1.4. Create convert (Iterator <Iterator>)" from the course job4j
 *
 * @author Nurgalimov Ilshat
 * @version 1.0
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private final Iterator<Iterator<Integer>> iterators = it;
            private Iterator<Integer> iterator = iterators.next();

            @Override
            public boolean hasNext() {
                boolean result = true;
                while (!iterator.hasNext()) {
                    while (iterators.hasNext()) {
                        iterator = iterators.next();
                        break;
                    }
                    if (!iterators.hasNext() && !iterator.hasNext()) {
                        result = false;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }
}