package ru.inurgalimov.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author Ilshat Nurgalimov
 * @version 01.02.2019
 */
public class Server {
    private File file;
    private BufferedReader in;
    private OutputStream out;

    public Server(String path, int port) throws IOException {
        this.file = new File(path);
        Socket socket = new ServerSocket(port).accept();
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = socket.getOutputStream();
    }

    public static void main(String[] args) {
        try {
            Server server = new Server(args[0], Integer.parseInt(args[1]));
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        this.out.write("Вы подключились к серверу! Для отключения введите 'отключить'".getBytes());
        String messageFromClient;
        File[] files;
        Stack<File> stack = new Stack<>();
        do {
            files = file.listFiles();
            this.out.write(files.toString().getBytes());
            messageFromClient = this.in.readLine();
            if (!messageFromClient.equals("отключить")) {
                if (messageFromClient.equals("...") && !stack.empty()) {
                    file = stack.pop();
                } else {
                    for (File f : files) {
                        if (f.getName().equals(messageFromClient)) {
                            if (f.isDirectory()) {
                                stack.push(file);
                                file = f;
                            } else {
                                out.write("скачивание".getBytes());
                                out.write(f.getName().getBytes());
                                try(FileInputStream fis = new FileInputStream(f)) {
                                    out.write(fis.readAllBytes());
                                }
                            }
                        }
                    }
                }
            }
        } while (!messageFromClient.equals("отключить"));
        this.out.write("выход".getBytes());
        this.in.close();
        this.out.close();
    }
}
