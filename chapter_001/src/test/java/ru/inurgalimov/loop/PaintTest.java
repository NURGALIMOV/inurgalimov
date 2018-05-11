package ru.inurgalimov.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class PaintTest {
    @Test
    public void whenPyramid2() {
        Paint paint = new Paint();
        String result = paint.pyramid(2);
        System.out.println(result);
        assertThat(result,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add(" ^ ")
                                .add("^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid3() {
        Paint paint = new Paint();
        String result = paint.pyramid(3);
        System.out.println(result);
        assertThat(result,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("  ^  ")
                                .add(" ^^^ ")
                                .add("^^^^^")
                                .toString()
                )
        );
    }
}
