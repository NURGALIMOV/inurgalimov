package ru.inurgalimov.io;

import java.io.*;
import java.util.Scanner;

/**
 * @author Nurgalimov Ilsha
 * @version 25.01.2019
 */
public class Chat {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             RandomAccessFile input =
                     new RandomAccessFile(new File("src/main/resources/", "source.txt"), "r");
             FileOutputStream output = new FileOutputStream(new File("src/main/resources/", "log.txt"))) {
            System.out.println("Введите слово:");
            String messageClient = bufferedReader.readLine();
            while (!messageClient.equals("закончить")) {
                output.write((messageClient + "\n").getBytes());
                if (messageClient.equals("стоп")) {
                    while (!messageClient.equals("продолжить")) {
                        messageClient = bufferedReader.readLine();
                        output.write((messageClient + "\n").getBytes());
                    }
                } else {
                    long l = (long) (0 + Math.random() * input.length());
                    input.seek(l);
                    String[] answer = input.readLine().split(" ");
                    int i = (int) (0 + Math.random() * answer.length);
                    String s = answer[i];
                    System.out.println(s);
                    output.write((s + "\n").getBytes());
                    messageClient = bufferedReader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
