package ru.inurgalimov.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CheckInputStreamTest {
    @Test
    public void whenWeCheckAnEvenNumber() {
        System.setIn(new ByteArrayInputStream("100".getBytes()));
        assertThat(new CheckInputStream().isNumber(System.in), is(true));
    }

    @Test
    public void whenWeCheckNotAnEvenNumber() {
        System.setIn(new ByteArrayInputStream("5".getBytes()));
        assertThat(new CheckInputStream().isNumber(System.in), is(false));
    }

    @Test
    public void whenWeCheckNotANumber() {
        System.setIn(new ByteArrayInputStream("k".getBytes()));
        assertThat(new CheckInputStream().isNumber(System.in), is(false));
    }
}