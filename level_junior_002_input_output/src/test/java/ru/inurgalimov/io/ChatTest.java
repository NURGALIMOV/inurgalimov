package ru.inurgalimov.io;

import org.junit.Test;

public class ChatTest {
    @Test
    public void whenWeCommunicateWithTheClient() {
        Chat chat = new Chat();
        chat.communication("C:\\java\\inurgalimov\\level_junior_002_input_output\\src\\main\\resources", "source.txt");

    }
}