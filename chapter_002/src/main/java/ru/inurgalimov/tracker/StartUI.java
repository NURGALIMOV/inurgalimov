package ru.inurgalimov.tracker;

public class StartUI {
    private static final int EXIT = 6;
    private final Input input;
    private final ITracker tracker;
    private final MenuTracker menuTracker;
    private int[] range;

    public StartUI(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
        this.menuTracker = new MenuTracker(input, tracker);
    }

    public void init() {
        this.menuTracker.fillActions();
        this.range = this.menuTracker.rangeArray();
        boolean exit = false;
        while (!exit) {
            this.menuTracker.showMenu((a) -> {
                System.out.println("Меню.");
                for (UserAction action : a) {
                    System.out.println(action.info());
                }
            });
            int answer = this.input.ask("Введите пункт меню : ", range);
            menuTracker.select(answer);
            if (EXIT == answer) {
                System.out.println("Выход из программы!");
                exit = true;
            }
        }
    }

    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
