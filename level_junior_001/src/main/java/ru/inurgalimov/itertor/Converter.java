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
                boolean result = false;
                /*if (iterator == null && iterators.hasNext()) {
                    iterator = iterators.next();
                    if (!iterator.hasNext()) {
                        result = hasNext();
                    }
                } else if (iterator != null && !iterator.hasNext()) {
                    if (iterators.hasNext()) {
                        iterator = iterators.next();
                        if (!iterator.hasNext()) {
                            result = hasNext();
                        }
                    } else {
                        result = false;
                    }
                } else if (!iterators.hasNext() && !iterator.hasNext()) {
                    result = false;
                }*/
                while (!iterator.hasNext()) {
                    if(iterators.hasNext()) {
                        iterator = iterators.next();
                        result = true;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                Integer integer = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (hasNext()) {
                    integer = iterator.next();
                }
                return integer;
            }
        };
    }
}
