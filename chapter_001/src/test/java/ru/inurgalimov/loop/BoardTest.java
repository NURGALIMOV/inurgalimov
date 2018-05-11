package ru.inurgalimov.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String LINE = System.getProperty("line.separator");
        String expected = String.format("X X%s X %sX X%s", LINE, LINE, LINE);
        assertThat(result, is(expected));
    }

    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        //напишите здесь тест, проверяющий формирование доски 5 на 4.
        Board board = new Board();
        String result = board.paint(5, 4);
        final String LINE = System.getProperty("line.separator");
        String expected = String.format("X X X%s X X %sX X X%s X X %s", LINE, LINE, LINE, LINE);
        assertThat(result, is(expected));
    }
}
