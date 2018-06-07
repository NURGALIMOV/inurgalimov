package ru.inurgalimov.tracker;

public class StubInput implements Input {
    private final String[] value;
    private int position;

    public StubInput(final String[] value) {
        this.value = value;
        position = 0;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }
    @Override
    public int ask(String question, int[] range) {
        return Integer.valueOf(this.value[this.position++]);
    }
}