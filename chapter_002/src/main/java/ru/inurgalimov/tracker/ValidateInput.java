package ru.inurgalimov.tracker;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Введенное число не подходит, попробуйте снова.");
            } catch (NumberFormatException nfe) {
                System.out.println("Вы ввели не число.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(invalid);
        return value;
    }
}
