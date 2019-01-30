package ru.inurgalimov.socket.customer;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotCustomTest {
    private final String ln = System.getProperty("line.separator");

    @Test
    public void whenWeReceiveAResponseFromTheServer() {
        Socket socket = mock(Socket.class);
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ByteArrayInputStream in = new ByteArrayInputStream(Joiner.on(ln).join(
                "hello",
                "dear friend",
                ""
        ).getBytes());
        try {
            when(socket.getOutputStream()).thenReturn(out);
            when(socket.getInputStream()).thenReturn(in);
            BotCustom botCustom = new BotCustom(socket);
            botCustom.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(Joiner.on(ln).join(
                "hello",
                "hello",
                "dear friend",
                ""
        )));
        System.setOut(stdout);
    }
}