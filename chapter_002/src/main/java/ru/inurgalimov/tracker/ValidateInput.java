package ru.inurgalimov.tracker;

public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Введите правильно пункт меню.");
            } catch (NumberFormatException nfe) {
                System.out.println("Введенные данные не верны, попробуйте снова.");
            }
        } while (invalid);
        return value;
    }
}
