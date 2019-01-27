package ru.inurgalimov.socket.server;

import java.io.*;
import java.net.*;

public class BotServer {
    private final Socket socket;

    public BotServer(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!"exit".equals(ask)) {
                    out.println("I do not understand!");
                    out.println();
                }
            } while (!"exit".equals(ask));
        }
    }

    public static void main(String[] args) {
        try {
            new BotServer(new ServerSocket(Integer.parseInt(args[0])).accept()).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
