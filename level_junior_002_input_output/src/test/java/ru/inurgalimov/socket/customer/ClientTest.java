package ru.inurgalimov.socket.customer;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {
    private final String ln = System.getProperty("line.separator");

    @Test
    public void clientStart() {
        Socket socket = mock(Socket.class);
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ByteArrayInputStream in = new ByteArrayInputStream("...".getBytes());
        try {
            when(socket.getOutputStream()).thenReturn(out);
            when(socket.getInputStream()).thenReturn(in);
            Client client = new Client("127.0.0.1", 5000);
            client.start(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is("...\r\n"));
        System.setOut(stdout);
    }
}