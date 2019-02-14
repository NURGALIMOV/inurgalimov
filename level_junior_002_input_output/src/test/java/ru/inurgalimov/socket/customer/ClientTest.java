package ru.inurgalimov.socket.customer;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void ClientStart() {
        try {
            Client client = new Client("127.0.0.1", 5000);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}