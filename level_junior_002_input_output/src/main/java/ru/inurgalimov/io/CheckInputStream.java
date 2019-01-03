package ru.inurgalimov.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
