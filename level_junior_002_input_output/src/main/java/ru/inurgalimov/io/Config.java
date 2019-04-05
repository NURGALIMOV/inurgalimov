package ru.inurgalimov.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Task: "1. Читаем файл конфигурации"
 * Задание.
 * 1. Реализуйте метод load по аналогии с методом toString. Метод load должен загружать пару ключ-значение в Map values.
 * 2. Реализуйте метод value(String key) он должен возвращать значение ключа.
 * 3. Напишите тест ConfigTest.
 *
 * @author Ilshat Nurgalimov
 * @version 05.04.2019
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try(BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(a ->  {
                if(!a.startsWith("#") && !a.equals("")) {
                    String[] temp = a.split("=");
                    values.put(temp[0], temp[1]);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
