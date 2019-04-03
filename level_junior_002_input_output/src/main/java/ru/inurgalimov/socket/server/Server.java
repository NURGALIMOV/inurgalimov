package ru.inurgalimov.socket.server;

import ru.inurgalimov.socket.NextDirectory;
import ru.inurgalimov.socket.SimpleActionServer;
import ru.inurgalimov.socket.UpDirectory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @author Ilshat Nurgalimov
 * @version 03.04.2019
 */
public class Server {
    private File file;
    private ServerSocket serverSocket;
    private static final String PROCEED = "proceed";
    private static final String EXIT = "exit";
    private static final String DOWNLOAD = "download";
    private static final String LN = System.getProperty("line.separator");
    private Map<String, SimpleActionServer> actions = new HashMap<>();

    public Server(String path, int port) {
        try {
            this.file = new File(path);
            this.serverSocket = new ServerSocket(port);
            actions.put("...", new UpDirectory());
            actions.put("next", new NextDirectory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(args[0], Integer.parseInt(args[1]));
        try (Socket socket = server.serverSocket.accept()) {
            server.start(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Запускаем сервер.
     *
     * @param socket - socket полученный при подключении клиента к серверу.
     * @throws IOException
     */
    public void start(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            File currentFile = file;
            List<File> list;
            String messageFromClient = EXIT;
            do {
                list = Arrays.asList(currentFile.listFiles());
                print(out, list);
                sendMessage(out, PROCEED);
                messageFromClient = reader.readLine();
                System.out.println(messageFromClient);
                String[] message = messageFromClient.split(" ");

                if (!EXIT.equals(message[0].toLowerCase())) {
                    currentFile = actions.get(
                            message[0]).action(
                                    currentFile, file, messageFromClient.replaceAll(
                                            message[0] + " ", ""
                            )
                    );
                    if (currentFile.isFile()) {
                        sendFile(out, currentFile);
                        currentFile = currentFile.getParentFile();
                    }
                }
            } while (!EXIT.equals(messageFromClient.toLowerCase()));
            sendMessage(out, EXIT);
        }
    }

    /**
     * Отправляем файл клиенту.
     *
     * @param out  - поток для отправки.
     * @param file - файл для отправки.
     * @throws IOException
     */
    private void sendFile(OutputStream out, File file) throws IOException {
        sendMessage(out, DOWNLOAD);
        sendMessage(out, file.getName());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int size = fileInputStream.available();
            sendMessage(out, size + "");
            byte[] buffer = new byte[1024];
            int temp;
            while (size > 0) {
                temp = fileInputStream.read(buffer);
                System.out.println(temp);
                out.write(buffer);
                out.flush();
                size = size - temp;
            }
        }
    }

    /**
     * Отправляем лист файлов клиенту в выбронной директории.
     *
     * @param out  - поток для отправки.
     * @param list - лист файлов текущей директории.
     * @throws IOException
     */
    private void print(OutputStream out, List<File> list) throws IOException {
        for (File f : list) {
            out.write((f.getName() + LN).getBytes());
        }
    }

    /**
     * Отправка служебных сообщений клиенту.
     *
     * @param out     - поток для отправки.
     * @param message - сообщение для клиента.
     * @throws IOException
     */
    private void sendMessage(OutputStream out, String message) throws IOException {
        out.write((message + LN).getBytes());
    }
}
