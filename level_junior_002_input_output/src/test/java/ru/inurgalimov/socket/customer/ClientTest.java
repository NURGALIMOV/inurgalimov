package ru.inurgalimov.socket.customer;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ClientTest {
    @Test
    public void clientStart() {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] args = {"127.0.0.1", "5000"};
            Client.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}