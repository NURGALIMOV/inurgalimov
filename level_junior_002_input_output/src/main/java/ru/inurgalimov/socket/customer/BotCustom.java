package ru.inurgalimov.socket.customer;

import java.io.*;
import java.net.*;
import java.util.*;

public class BotCustom {
    private final Socket socket;

    public BotCustom(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        try {
            new BotCustom(new Socket(InetAddress.getByName(args[0]), Integer.parseInt(args[1]))).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            Scanner console = new Scanner(System.in);
            do {
                out.println("hello");
                String str = in.readLine();
                while (!(str).isEmpty()) {
                    System.out.println(str);
                    str = in.readLine();
                }
            } while (true);
        }
    }
}
