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
    private Scanner scanner;

    public Client(String adress, int port) {
        try {
            this.socket = new Socket(InetAddress.getByName(adress), port);
            this.scanner = new Scanner(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
        try {
            InputStream in = client.getSocket().getInputStream();
            OutputStream out = client.getSocket().getOutputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                 Scanner scanner = new Scanner(System.in)) {
                String messageFromServer;
                String messageToServer;
                while (true) {
                    while (true) {
                        messageFromServer = reader.readLine();
                        if (messageFromServer.toLowerCase().equals("exit") ||
                                messageFromServer.toLowerCase().equals("continue")) {
                            break;
                        }
                        System.out.println(messageFromServer);
                        if (messageFromServer.equals("download")) {
                            byte[] bufer = new byte[1024];
                            File tempFile = new File("C:\\soft\\", reader.readLine());
                            try (FileOutputStream fileOutputStream =
                                         new FileOutputStream(tempFile)) {
                                long size = Long.parseLong(reader.readLine());
                                while (in.available() != 0) {
                                    fileOutputStream.write(in.read());
                                }
                                System.out.println("finish");
                            }
                        }
                    }
                    if (messageFromServer.toLowerCase().equals("exit")) {
                        break;
                    }
                    messageToServer = scanner.nextLine();
                    out.write((messageToServer + "\n").getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.getSocket().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() throws Exception {
    }

    public Socket getSocket() {
        return socket;
    }
}
