package ru.inurgalimov.io;

import java.io.*;
import java.util.Arrays;

/**
 * @author Nurgalimov Ilshat
 * @version 1.01.2019
 */
public class CheckInputStream {
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
            Arrays.stream(br.readLine().split("\\W")).filter(a -> {
                boolean result = true;
                for (String s : abuse) {
                    if (s.equals(a)) {
                        result = false;
                        break;
                    }
                }
                return result;
            }).forEach(a -> {
                try {
                    bw.write(a + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
