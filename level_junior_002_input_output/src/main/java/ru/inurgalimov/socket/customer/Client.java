package ru.inurgalimov.socket.customer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Ilshat Nurgalimov
 * @version 01.02.2019
 */
public class Client {
    private Socket socket;
    private BufferedReader in;
    private OutputStream out;
    private Scanner scanner;

    public Client(String adress, int port) throws Exception {
        this.socket = new Socket(InetAddress.getByName(adress), port);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = this.socket.getOutputStream();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        try {
            Client client = new Client(args[0], Integer.parseInt(args[1]));
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        String str = this.in.readLine();
        System.out.println(str);
        do {
            str = this.in.readLine();
            System.out.println(str);
            out.write(scanner.nextLine().getBytes());
            if (str.equals("скачивание")) {
                try (FileOutputStream fos = new FileOutputStream(new File("src/main/resources/", in.readLine()))) {
                    fos.write(in.read());
                }
            }
        } while(!str.equals("выход"));
    }
}
