package ru.inurgalimov.socket.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotServerTest {
    private final String ln = System.getProperty("line.separator");

    @Test
    public void whenWeDisconnect() {
        this.testServer("exit", "");
    }

    @Test
    public void whenWeGreetTheServer() {
        this.testServer(Joiner.on(ln).join(
                "hello",
                "exit"),
                Joiner.on(ln).join(
                        "Hello, dear friend, I'm a oracle.",
                        "",
                        ""
                ));
    }

    @Test
    public void whenTheServerDoesNotUnderstandUs() {
        this.testServer(Joiner.on(ln).join(
                "not supported",
                "exit"),
                Joiner.on(ln).join(
                        "I do not understand!",
                        "",
                        ""
                ));
    }


    public void testServer(String input, String output) {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            when(socket.getInputStream()).thenReturn(in);
            when(socket.getOutputStream()).thenReturn(out);
            BotServer botServer = new BotServer(socket);
            botServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(out.toString(), is(output));
    }
}