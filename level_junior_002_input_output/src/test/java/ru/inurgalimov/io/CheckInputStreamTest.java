package ru.inurgalimov.io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CheckInputStreamTest {
    @Test
    public void whenWeCheckAnEvenNumber() {
        try (ByteArrayInputStream bIn = new ByteArrayInputStream("100".getBytes())) {
            System.setIn(bIn);
            assertThat(new CheckInputStream().isNumber(System.in), is(true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenWeCheckNotAnEvenNumber() {
        try (ByteArrayInputStream bIn = new ByteArrayInputStream("5".getBytes())) {
            System.setIn(bIn);
            assertThat(new CheckInputStream().isNumber(System.in), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenWeCheckNotANumber() {
        try (ByteArrayInputStream bIn = new ByteArrayInputStream("k".getBytes())) {
            System.setIn(bIn);
            assertThat(new CheckInputStream().isNumber(System.in), is(false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}