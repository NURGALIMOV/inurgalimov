package ru.inurgalimov.io;

import java.io.*;
import java.util.Scanner;

/**
 * @author Nurgalimov Ilsha
 * @version 25.01.2019
 */
public class Chat {
    public void communication(String answerPath, String answerName) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             RandomAccessFile input = new RandomAccessFile(new File(answerPath, answerName), "r");
             FileOutputStream output = new FileOutputStream(new File("src/main/resources/", "log.txt"))) {
            System.out.println("Введите слово");
            String messageClient = bufferedReader.readLine();
            while (!messageClient.equals("закончить")) {
                output.write((messageClient + "\n").getBytes());
                if (messageClient.equals("стоп")) {
                    while (!messageClient.equals("продолжить")) {
                        messageClient = bufferedReader.readLine();
                        output.write((messageClient + "\n").getBytes());
                    }
                } else {
                    input.seek((long) Math.random() * input.length());
                    String[] answer = input.readLine().split("\\w");
                    String s = answer[(int) Math.random() * answer.length];
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
