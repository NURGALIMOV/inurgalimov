package main.java.ru.nurgalimov.jmm;

public class Resource {
    private String msg;

    public void printMessage(String text) {
        this.msg = text;
        System.out.println("Привет из потока " + msg + "!");
    }
}
