package ru.inurgalimov.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nurgalimov Ilshat
 * @version 5.01.2019
 */
public class CheckInputStream implements FileSorting {
    /**
     * Реализовать сервис boolean isNumber(InputStream in);
     * метод должен проверить, что в байтовом потоке записано четное число.
     * Все потоки должны быть обернуты через try-resources. Даже, если это ByteArrayInputStream
     *
     * @param io
     * @return boolean, the result of checking that an even number is written in the byte stream.
     */
    public boolean isNumber(InputStream io) {
        boolean result = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(io))) {
            if (Integer.parseInt(br.readLine()) % 2 == 0) {
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Реализовать сервис void dropAbuses(InputStream in, OutputStream out, String[] abuse);
     * Задан символьным поток и символьный выходной поток, надо удалить все слова abuse.
     * Важно все преобразования нужно делать в потоке, нельзя зачитать весь поток в память,
     * удалить слова и потом записать, нужно все делать в потоке.
     *
     * @param in,    входной поток.
     * @param out,   выходной поток.
     * @param abuse, список запрещённых слов.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
            for (String a : br.readLine().split("\\W")) {
                if (!Arrays.stream(abuse).allMatch(b -> a.equals(b))) {
                    bw.write(a + " ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. Есть файл размер более 3G.
     * 2. Файл тестовый. В каждой строке записана строка - длинной n.
     * 3. Нужно реализовать интерфейс sort(File source, File distance);
     * 4. Необходимо отсортировать файл по возрастанию длин строк, использовать внешнюю сортировку и RandomAccessFile.
     *
     * @param source   -  это txt файл. со строками.
     * @param distance -  несуществующий. Файл, его надо создать и записать туда результат сортировки.
     */
    @Override
    public void sort(File source, File distance) {
        try (RandomAccessFile readSource = new RandomAccessFile(source, "r");
             RandomAccessFile writeDistance = new RandomAccessFile(distance, "rw")) {
            byte[] bytes = new byte[1048576];
            int i = 0;
            List<File> filesBefore = new ArrayList<>();
            List<File> files = new ArrayList<>();
            File tmpFile;
            while (readSource.read(bytes) != -1) {
                i++;
                tmpFile = new File("src/main/java/", "tmp" + i + ".txt");
                try (RandomAccessFile tmpR = new RandomAccessFile(tmpFile, "rw")) {
                    tmpR.write(bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                filesBefore.add(tmpFile);
            }
            for (File f : filesBefore) {
                i++;
                tmpFile = new File("src/main/java/", "tmp" + i + ".txt");
                try (RandomAccessFile tmpR = new RandomAccessFile(f, "r");
                     RandomAccessFile tmp = new RandomAccessFile(tmpFile, "rw")) {
                    List<String> list = new ArrayList<>();
                    String str = tmpR.readLine();
                    while (str != null) {
                        list.add(str);
                        str = tmpR.readLine();
                    }
                    list = list.stream().sorted((a, b) -> a.length() - b.length()).collect(Collectors.toList());
                    for (String s : list) {
                        tmp.writeUTF(s + "\n");
                    }
                    files.add(tmpFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                f.delete();
            }
            while (files.size() != 1) {
                for (int k = 0, j = 1; j < files.size(); k = k + 2, j = j + 2) {
                    try (RandomAccessFile r1 = new RandomAccessFile(files.get(k), "r");
                         RandomAccessFile r2 = new RandomAccessFile(files.get(j), "rw")) {
                        List<String> l1 = new ArrayList<>();
                        List<String> l2 = new ArrayList<>();
                        String s1 = r1.readLine();
                        String s2 = r2.readLine();
                        while (s1 != null || s2 != null) {
                            if (s1 != null) {
                                l1.add(s1);
                                s1 = r1.readLine();
                            }
                            if (s2 != null) {
                                l2.add(s2);
                                s2 = r2.readLine();
                            }
                        }
                        List<String> listStringForResultFile = new ArrayList<>();
                        for (int a = 0, b = 0; a < l1.size() && b < l2.size(); ) {
                            listStringForResultFile.add(l1.get(a).length() < l1.get(b).length() ? l1.get(a++)
                                    : l2.get(b++));
                        }
                        for (String s : listStringForResultFile) {
                            r2.writeUTF(s + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                for (int h = 0; h < files.size(); h++) {
                    files.get(h).delete();
                    files.remove(h);
                }
            }
            try(RandomAccessFile raf = new RandomAccessFile(files.get(0), "r")) {
                String t = raf.readLine();
                while(t != null) {
                    writeDistance.writeUTF(t + "\n");
                    t = raf.readLine();
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
