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
            private Iterator<Integer> iterator;

            @Override
            public boolean hasNext() {
                return check();
            }

            @Override
            public Integer next() {
                Integer integer = null;
                if (!check()) {
                    throw new NoSuchElementException();
                } else if (check()) {
                    integer = iterator.next();
                }
                return integer;
            }

            /**
             * The method is needed to verify the next step
             *
             * @return boolean
             */
            private boolean check() {
                boolean result = true;
                if (iterator == null && iterators.hasNext()) {
                    iterator = iterators.next();
                    if (!iterator.hasNext()) {
                        result = check();
                    }
                } else if (iterator != null && !iterator.hasNext()) {
                    if (iterators.hasNext()) {
                        iterator = iterators.next();
                        if (!iterator.hasNext()) {
                            result = check();
                        }
                    } else {
                        result = false;
                    }
                } else if (!iterators.hasNext() && !iterator.hasNext()) {
                    result = false;
                }
                return result;
            }
        };
    }
}
