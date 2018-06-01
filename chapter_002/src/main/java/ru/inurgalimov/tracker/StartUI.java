package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

public class StartUI {
    private static final String ADD = "0";
    private static final String SHOW = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";
    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.printListItem(this.tracker.findAll());
            } else if (EDIT.equals(answer)) {
                this.editItem(input.ask("Введите ID заявки для корректировки: "));
            } else if (DELETE.equals(answer)) {
                this.tracker.delete(input.ask("Введите ID заявки для удаления:"));
            } else if (FINDID.equals(answer)) {
                this.printItem(input.ask("Введите ID заявки для поиска:"));
            } else if (FINDNAME.equals(answer)) {
                printListItem(this.tracker.findByName(input.ask("Введите имя заявки для поиска:")));
            } else if (EXIT.equals(answer)) {
                System.out.println("Выход из программы!");
                exit = true;
            } else {
                System.out.println("Вы ввели не корректное значение!");
            }
        }
    }

    private void printItem(String id) {
        System.out.println("Имя заявки: " + this.tracker.findById(id).getName());
        System.out.println("Описание заявки: " + this.tracker.findById(id).getDescription());
        System.out.println("ID заявки: " + this.tracker.findById(id).getId() + "\n");
        System.out.println();
    }

    private void printListItem(Item[] it) {
        for (Item item : it) {
            System.out.println("Имя заявки: " + item.getName());
            System.out.println("Описание заявки: " + item.getDescription());
            System.out.println("ID заявки: " + item.getId() + "\n");
        }
        System.out.println();
    }

    private void editItem(String id) {
        System.out.println("------------ Корректировка заявки --------------");
        String name = this.input.ask("Введите новое имя для заявки :");
        String desc = this.input.ask("Введите новое описание для заявки :");
        this.tracker.findById(id).setName(name);
        this.tracker.findById(id).setDescription(desc);
    }

    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
