package ru.inurgalimov.io;

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Nurgalimov Ilshat
 * @version 09.01.2019
 */

public class SortingFileMultithreading {
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
            BlockingQueue<File> filesBeforeSorting = new LinkedBlockingQueue<>();
            Thread share = shareFile(readSource, filesBeforeSorting);
            BlockingQueue<File> filesAfterSorting = new LinkedBlockingQueue<>();
            Thread sortFiles = sortTheContentsOfFiles(filesBeforeSorting, filesAfterSorting, share);
            Thread combine = combineFiles(filesAfterSorting, writeDistance, sortFiles);
            share.start();
            sortFiles.start();
            combine.start();
            share.join();
            sortFiles.join();
            combine.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Делим исходный файл на куски в отдельном потоке.
     *
     * @param readSource, объект RandomAccessFile для чтения исходного файла.
     * @param files,      блокирующая очередь для вставки разделенных файлов.
     * @return возвращаем объект Thread.
     */
    private Thread shareFile(RandomAccessFile readSource, BlockingQueue<File> files) {
        return new Thread(() -> {
            try {
                long numberOfParts = readSource.length() / 1048576;
                File tempFile;
                for (long i = numberOfParts; i > 0; i--) {
                    tempFile = new File("src/main/resources/", "temp" + i + ".txt");
                    try (RandomAccessFile writer = new RandomAccessFile(tempFile, "rw")) {
                        while (readSource.getFilePointer() < readSource.length() / i) {
                            writer.write((readSource.readLine() + "\n").getBytes());
                        }
                    }
                    files.put(tempFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Сортируем содержимое разделенных файлов в отдельном потоке.
     *
     * @param filesBefore, очередь не отсортированных файлов.
     * @param filesAfter,  очередь отсортированных файлов.
     * @param t,           поток использующий не отсортированную очередь.
     * @return возвращаем объект Thread.
     */
    private Thread sortTheContentsOfFiles(BlockingQueue<File> filesBefore, BlockingQueue<File> filesAfter, Thread t) {
        return new Thread(() -> {
            File fB;
            while (t.isAlive()) {
                if (!filesBefore.isEmpty()) {
                    try {
                        fB = filesBefore.take();
                        try (RandomAccessFile r = new RandomAccessFile(fB, "rw")) {
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
                            filesAfter.put(fB);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * Объединяем файлы.
     *
     * @param files          очередь файлов с отсортированным содержимым.
     * @param writeDistance, обхект RandomAccessFile для записи в конечный файл.
     * @param thread,        поток параллельно использующий очередь files.
     * @return возвращаем объект Thread.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private Thread combineFiles(BlockingQueue<File> files, RandomAccessFile writeDistance, Thread thread) throws FileNotFoundException, IOException {
        return new Thread(() -> {
            File tempFile;
            int tempCount = 0;
            while ((files.size() != 1) || thread.isAlive()) {
                tempFile = new File("src/main/resources/", "tempFile" + tempCount++ + ".txt");
                try (RandomAccessFile r1 = new RandomAccessFile(files.take(), "r");
                     RandomAccessFile r2 = new RandomAccessFile(files.take(), "rw");
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
                    files.put(tempFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try (RandomAccessFile raf = new RandomAccessFile(files.take(), "r")) {
                String t;
                while ((t = raf.readLine()) != null) {
                    writeDistance.write((t + "\n").getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
