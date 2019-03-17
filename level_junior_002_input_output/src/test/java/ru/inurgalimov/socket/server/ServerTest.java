package ru.inurgalimov.socket.server;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    @Test
    public void testServer() {
        String output = "log.txt\r\nsource.txt\r\nproceed\r\nexit\r\n";
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            Server server = new Server("src\\main\\resources", 5000);
            server.start(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(output));
    }
}