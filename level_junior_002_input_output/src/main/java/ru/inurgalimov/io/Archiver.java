package ru.inurgalimov.io;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивируем папку.
 *
 * @author Nurgalimov Ilshat
 * @version 19.01.2019
 */
public class Archiver {
    /**
     * Точка входа в программу
     *
     * @param args, args[0] - исходная папка, args[1] - список расширений файлов для архивации через запятую ,
     *              args[2] - название конечного архива.
     */
    public static void main(String[] args) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(args[2]))) {
            File file = new File(args[0]);
            String[] fileName = args[1].split(",");
            doZip(file, out, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Находим все необходимые файлы и передаём далее на запись.
     *
     * @param dir      - директория для архивации.
     * @param out      - наименование конечного архива.
     * @param fileName - список расширений файлов для архивации.
     * @throws IOException
     */
    private static void doZip(File dir, ZipOutputStream out, String[] fileName) throws IOException {
        Queue<File> fileQueue = new LinkedList<File>();
        Arrays.stream(dir.listFiles()).forEach(a -> fileQueue.add(a));
        File tempFile;
        while (!fileQueue.isEmpty()) {
            tempFile = fileQueue.poll();
            if (tempFile.isDirectory()) {
                Arrays.stream(tempFile.listFiles()).forEach(a -> fileQueue.add(a));
            } else {
                for (String s : fileName) {
                    if (tempFile.getName().endsWith(s)) {
                        out.putNextEntry(new ZipEntry(tempFile.getPath()));
                        write(new FileInputStream(tempFile), out);
                        out.closeEntry();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Записываем файл в архив.
     *
     * @param in  - stream для чтения файла.
     * @param out - stream для записи в архив.
     * @throws IOException
     */
    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        in.close();
    }
}