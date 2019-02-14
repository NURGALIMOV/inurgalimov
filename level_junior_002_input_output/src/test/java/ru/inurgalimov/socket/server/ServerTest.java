package ru.inurgalimov.socket.server;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void ServerSteart() {
        try {
            Server server = new Server("C:\\", 5000);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}