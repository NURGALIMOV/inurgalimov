package ru.inurgalimov.io;

import java.io.*;
import java.util.*;

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
            List<String> list = new ArrayList<>();
            String str = readSource.readLine();
            while(str != null) {
                list.add(str);
                str = readSource.readLine();
            }
            Collections.sort(list);
            for (String s : list) {
                writeDistance.writeChars(s + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
