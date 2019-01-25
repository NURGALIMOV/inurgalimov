package ru.inurgalimov.io;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Nurgalimov Ilsha
 * @version 25.01.2019
 */
public class СonsoleСhat {
    public void communication(String answerPath, String answerName) {
        try (Scanner scanner = new Scanner(System.in);
             RandomAccessFile input = new RandomAccessFile(new File(answerPath, answerName), "r");
             FileOutputStream output = new FileOutputStream(new File("src/main/resources/", "log.txt"))) {
            System.out.println("Введите слово");
            String messageClient = scanner.nextLine();
            while (!messageClient.equals("закончить")) {
                output.write((messageClient + "\n").getBytes());
                if (messageClient.equals("стоп")) {
                    while (!messageClient.equals("продолжить")) {
                        messageClient = scanner.nextLine();
                        output.write((messageClient + "\n").getBytes());
                    }
                } else {
                    input.seek((long) Math.random() * input.length());
                    String[] answer = input.readLine().split("\\w");
                    String s = answer[(int) Math.random() * answer.length];
                    System.out.println(s);
                    output.write((s + "\n").getBytes());
                    messageClient = scanner.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
