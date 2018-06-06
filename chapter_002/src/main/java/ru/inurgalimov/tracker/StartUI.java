package ru.inurgalimov.tracker;

public class StartUI {
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;
    private final MenuTracker menuTracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        this.menuTracker = new MenuTracker(input, tracker);
    }

    public void init() {
        this.menuTracker.fillActions();
        boolean exit = false;
        while (!exit) {
            this.menuTracker.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (Integer.valueOf(answer) >= 0 && Integer.valueOf(answer) < 6) {
                menuTracker.select(Integer.valueOf(answer));
            } else if (EXIT.equals(answer)) {
                System.out.println("Выход из программы!");
                exit = true;
            } else {
                System.out.println("Вы ввели не корректное значение!");
            }
        }
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
