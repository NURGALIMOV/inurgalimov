package ru.inurgalimov.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Ilshat Nurgalimov
 * @version 01.02.2019
 */
public class Server {
    private File file;
    private ServerSocket serverSocket;

    public Server(String path, int port) {
        try {
            this.file = new File(path);
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server("C:\\", 5000);
        try (Socket socket = server.serverSocket.accept()) {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                File currentFile = server.getFile();
                List<File> list;
                String messageFromClient = "exit";
                do {
                    list = Arrays.asList(currentFile.listFiles());
                    for (File f : list) {
                        out.write((f.getName() + "\n").getBytes());
                    }
                    out.write("continue\n".getBytes());
                    messageFromClient = reader.readLine();
                    System.out.println(messageFromClient);
                    if (!messageFromClient.toLowerCase().equals("exit")) {
                        if (messageFromClient.equals("...")) {
                            if (currentFile.equals(server.getFile())) {
                                continue;
                            } else {
                                currentFile = currentFile.getParentFile();
                            }
                        } else {
                            for (File f : list) {
                                if (f.getName().equals(messageFromClient)) {
                                    currentFile = f;
                                    break;
                                }
                            }
                        }
                        if (currentFile.isFile()) {
                            out.write("download\n".getBytes());
                            out.write((currentFile.getName() + "\n").getBytes());
                            out.write((currentFile.length() + "\n").getBytes());
                            try (FileInputStream fileInputStream = new FileInputStream(currentFile)) {
                                byte[] bufer = new byte[1024];
                                while(fileInputStream.available() != 0) {
                                    fileInputStream.read();
                                    out.write(bufer);
                                }
                            }
                            currentFile = currentFile.getParentFile();
                        }
                    }
                } while (!messageFromClient.toLowerCase().equals("exit"));
                out.write("exit\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

    }
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    public File getFile() {
        return file;
    }
}
