package ru.inurgalimov.socket.customer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Ilshat Nurgalimov
 * @version 01.03.2019
 */
public class Client {
    private Socket socket;
    private String EXIT = "exit";
    private String DOWNLOAD = "download";
    private String CONTINUE = "continue";
    private String FINISH = "finish";
    private String PATH = "level_junior_002_input_output\\src\\main\\resources";
    private final String LN = System.getProperty("line.separator");

    public Client(String adress, int port) {
        try {
            this.socket = new Socket(InetAddress.getByName(adress), port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client(args[0], Integer.parseInt(args[1]));
        try (Scanner scanner = new Scanner(System.in)) {
            client.start(client.socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Запускаем клиент
     *
     * @param socket - для взаимодействия с сервером.
     * @throws Exception
     */
    public void start(Socket socket) throws Exception {
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                 Scanner scanner = new Scanner(System.in)) {
                String messageFromServer;
                while (true) {
                    while (true) {
                        messageFromServer = reader.readLine();
                        if (messageFromServer.toLowerCase().equals(EXIT) ||
                                messageFromServer.toLowerCase().equals(CONTINUE)) {
                            break;
                        }
                        System.out.println(messageFromServer);
                        if (messageFromServer.equals(DOWNLOAD)) {
                            receiveFile(in, reader.readLine(), reader.readLine());
                        }
                    }
                    if (messageFromServer.toLowerCase().equals(EXIT)) {
                        break;
                    }
                    out.write((scanner.nextLine() + LN).getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получаем файл с севрера.
     *
     * @param in   - поток для чтения байтов, передаваемого файла.
     * @param name - имя нового файла для копирования данных.
     * @param size - размер файла.
     * @throws IOException
     */
    private void receiveFile(InputStream in, String name, String size) throws IOException {
        int length = Integer.parseInt(size);
        byte[] buffer = new byte[1024];
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(new File(PATH, name))) {
            int temp;
            while (length > 0) {
                temp = in.read(buffer);
                fileOutputStream.write(buffer);
                fileOutputStream.flush();
                length = length - temp;
                System.out.println(temp);
            }
        }
        System.out.println(FINISH);
    }
}