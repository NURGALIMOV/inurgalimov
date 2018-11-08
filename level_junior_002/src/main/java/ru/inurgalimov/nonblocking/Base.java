package ru.inurgalimov.nonblocking;

public class Base {
    private int id;
    private volatile int version;

    public Base(int i, int v) {
        this.id = i;
        this.version = v;
    }
    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int v) {
        this.version = v;
    }
}
