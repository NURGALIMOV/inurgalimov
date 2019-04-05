package ru.inurgalimov.io;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 2. Анализ доступности сервера.
 * Задание.
 * 1. Реализуйте метод unavailable.
 * source - имя файла лога
 * target - имя файла после анализа.
 * 2. Метод anavailable должен находить диапазоны, когда сервер не работал.
 * Сервер не работал. если status = 400 или 500.
 * Диапазон считается последовательность статусов 400 и 500
 * Например:
 * 200 10:56:01
 * 500 10:57:01
 * 400 10:58:01
 * 200 10:59:01
 * 500 11:01:02
 * 200 11:02:02
 * тут два периода - 10:57:01 до 10:59:01 и 11:01:02 до 11:02:02
 * Начальное время - это время когда статус 400 или 500. конечное время это когда статус меняется с 400 или 500 на
 * 200 300.
 * 3. Результат анализа нужно записать в файл target.
 * Формат файла
 * начала периода;конец периода;
 * 4. Записать тесты.
 *
 * @author Ilshat Nurgalimov
 * @version 05.04.2019
 */

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            AtomicBoolean check = new AtomicBoolean(false);
            reader.lines().forEach(a -> {
                try {
                    String[] temp = a.split(" ");
                    if (("400".equals(temp[0]) || "500".equals(temp[0])) && !check.get()) {
                        writer.write(temp[1] + ";");
                        check.set(true);
                    } else if (("200".equals(temp[0]) || "300".equals(temp[0])) && check.get()) {
                        writer.write(temp[1] + ";\n");
                        check.set(false);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
//            out.println("15:01:30;15:02:32");
//            out.println("15:10:30;23:12:32");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
