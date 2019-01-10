package ru.inurgalimov.io;

import java.io.*;
import java.util.*;

/**
 * @author Nurgalimov Ilshat
 * @version 10.01.2019
 */

public class SortingFile {
    /**
     * 1. Есть файл размер более 3G.
     * 2. Файл тестовый. В каждой строке записана строка - длинной n.
     * 3. Нужно реализовать интерфейс sort(File source, File distance);
     * 4. Необходимо отсортировать файл по возрастанию длин строк, использовать внешнюю сортировку и RandomAccessFile.
     *
     * @param source   -  это txt файл со строками.
     * @param distance -  несуществующий Файл, его надо создать и записать туда результат сортировки.
     */

    public void sort(File source, File distance) {
        try (RandomAccessFile readSource = new RandomAccessFile(source, "r");
             RandomAccessFile writeDistance = new RandomAccessFile(distance, "rw")) {
            combineFiles(sortTheContentsOfFiles(shareFile(readSource)), writeDistance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Делим исходный файл на куски
     *
     * @param readSource, объект RandomAccessFile для чтения исходного файла.
     * @return возвращаем список файлов, в каждом файле кусок информации с исходного файла.
     */
    private List<File> shareFile(RandomAccessFile readSource) throws IOException {
        long numberOfParts = readSource.length() / 1048576;
        List<File> files = new ArrayList<>();
        File tempFile;
        for (long i = numberOfParts; i > 0; i--) {
            tempFile = new File("src/main/resources/", "temp" + i + ".txt");
            files.add(tempFile);
            try (RandomAccessFile writer = new RandomAccessFile(tempFile, "rw")) {
                while (readSource.getFilePointer() < readSource.length() / i) {
                    writer.write((readSource.readLine() + "\n").getBytes());
                }
            }
        }
        return files;
    }

    /**
     * Сортируем содержимое разделенных файлов.
     *
     * @param files, список файлов для сортировки содержимого.
     * @return возвращаем список файлов с отсортированным содержимым.
     * @throws IOException
     */
    private List<File> sortTheContentsOfFiles(List<File> files) throws IOException {
        for (File f : files) {
            try (RandomAccessFile r = new RandomAccessFile(f, "rw")) {
                List<String> tempList = new ArrayList<>();
                String temp;
                while ((temp = r.readLine()) != null) {
                    tempList.add(temp);
                }
                tempList.sort((a, b) -> a.length() - b.length());
                r.seek(0);
                for (String s : tempList) {
                    r.write((s + "\n").getBytes());
                }
            }
        }
        return files;
    }

    /**
     * Объединяем файлы.
     *
     * @param files список файлов с отсортированным содержимым.
     * @return возвращаем конечный файл.
     */
    private void combineFiles(List<File> files, RandomAccessFile writeDistance) throws FileNotFoundException, IOException {
        File tempFile;
        int tempCount = 0;
        while (files.size() != 1) {
            tempFile = new File("src/main/resources/", "tempFile" + tempCount++ + ".txt");
            try (RandomAccessFile r1 = new RandomAccessFile(files.get(0), "r");
                 RandomAccessFile r2 = new RandomAccessFile(files.get(1), "rw");
                 RandomAccessFile r3 = new RandomAccessFile(tempFile, "rw")) {
                String s1 = r1.readLine();
                String s2 = r2.readLine();
                String s3;
                while (s1 != null || s2 != null) {
                    if (s1 != null && s2 != null) {
                        r3.write(s1.length() < s2.length() ? (s1 + "\n").getBytes() : (s2 + "\n").getBytes());
                        s3 = s1.length() < s2.length() ? (s1 = r1.readLine()) : (s2 = r2.readLine());
                        continue;
                    } else {
                        if (s1 == null) {
                            r3.write((s2 + "\n").getBytes());
                            s2 = r2.readLine();
                        }
                        if (s2 == null) {
                            r3.write((s1 + "\n").getBytes());
                            s1 = r1.readLine();
                        }
                    }
                }
            }
            files.get(0).delete();
            files.get(1).delete();
            files.remove(0);
            files.remove(0);
            files.add(tempFile);
        }
        try (RandomAccessFile raf = new RandomAccessFile(files.get(0), "r")) {
            String t;
            while ((t = raf.readLine()) != null) {
                writeDistance.write((t + "\n").getBytes());
            }
        }
        files.get(0).delete();
        files.remove(0);
    }
}
