package ru.inurgalimov.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class СonsoleСhatTest {
    @Test
    public void whenWeCommunicateWithTheClient() {
        СonsoleСhat chat = new СonsoleСhat();
        chat.communication("C:\\java\\inurgalimov\\level_junior_002_input_output\\src\\main\\resources", "source.txt");

    }
}