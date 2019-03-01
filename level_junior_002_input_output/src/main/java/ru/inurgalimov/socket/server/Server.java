package ru.inurgalimov.socket.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ilshat Nurgalimov
 * @version 01.03.2019
 */
public class Server {
    private File file;
    private ServerSocket serverSocket;
    private final String CONTINUE = "continue";
    private final String EXIT = "exit";
    private final String DOWNLOAD = "download";
    private final String PATHPARENT = "...";
    private final String LN = System.getProperty("line.separator");

    public Server(String path, int port) {
        try {
            this.file = new File(path);
            this.serverSocket = new ServerSocket(port);
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
                outputForPrintFileName(out, list);
                sendMessageToClient(out, CONTINUE);
                messageFromClient = reader.readLine();
                System.out.println(messageFromClient);
                if (!messageFromClient.toLowerCase().equals(EXIT)) {
                    if (messageFromClient.equals(PATHPARENT)) {
                        if (currentFile.equals(file)) {
                            continue;
                        } else {
                            currentFile = currentFile.getParentFile();
                        }
                    } else {
                        currentFile = checkMessage(currentFile, list, messageFromClient);
                    }
                    if (currentFile.isFile()) {
                        sendFile(out, currentFile);
                        currentFile = currentFile.getParentFile();
                    }
                }
            } while (!messageFromClient.toLowerCase().equals(EXIT));
            sendMessageToClient(out, EXIT);
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
        sendMessageToClient(out, DOWNLOAD);
        sendMessageToClient(out, file.getName());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int size = fileInputStream.available();
            sendMessageToClient(out, size + "");
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
    private void outputForPrintFileName(OutputStream out, List<File> list) throws IOException {
        for (File f : list) {
            out.write((f.getName() + LN).getBytes());
        }
    }

    /**
     * Проверка выбронного файла на предмет наличия.
     *
     * @param file    - текущая директория.
     * @param list    - лист файлов текущей директории.
     * @param message - сообщение от клиента, кторое должно содержать наименование новой директории для перехода или
     *                закачки.
     * @return - если с указанный в сообщение файл находим, возвращаем его, если нет возвращаем текущий файл.
     */
    private File checkMessage(File file, List<File> list, String message) {
        File result = file;
        for (File f : list) {
            if (f.getName().equals(message)) {
                result = f;
                break;
            }
        }
        return result;
    }

    /**
     * Отправка служебных сообщений клиенту.
     *
     * @param out     - поток для отправки.
     * @param message - сообщение для клиента.
     * @throws IOException
     */
    private void sendMessageToClient(OutputStream out, String message) throws IOException {
        out.write((message + LN).getBytes());
    }
}
