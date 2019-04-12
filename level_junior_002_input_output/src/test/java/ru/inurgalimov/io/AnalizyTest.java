package ru.inurgalimov.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenUnavailable() throws IOException {
        Analizy analizy = new Analizy();
        String temp = analizy.getClass().getResource("\\").getFile();
        String path = temp.substring(0, temp.indexOf("target")) + "src\\main\\resources\\";
        analizy.unavailable(path + "server.txt", path + "target.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(path + "target.txt"))) {
            assertThat(reader.readLine(), is("10:58:01;10:59:01;"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02;"));
        }
    }
}