package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void showMenu() {
        System.out.println("Меню.");
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
        System.out.println("6. Exit Program");
    }

    public void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindId();
        this.actions[5] = new FindName();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }


    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите, имя заявки:");
            String desc = input.ask("Введите, описание заявки:");
            tracker.add(new Item(name, desc));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }
    }

    private class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.delete(input.ask("Введите ID заявки для удаления:"));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    private class FindId implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки для поиска:");
            Item resultFind = tracker.findById(id);
            if (resultFind != null) {
                System.out.println("Имя заявки: " + resultFind.getName());
                System.out.println("Описание заявки: " + resultFind.getDescription());
                System.out.println("ID заявки: " + resultFind.getId() + "\n");
                System.out.println();
            } else if (resultFind == null) {
                System.out.println("Этой заявки нет в списке!");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    private class FindName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] it = tracker.findByName(input.ask("Введите имя заявки для поиска:"));
            for (Item item : it) {
                System.out.println("Имя заявки: " + item.getName());
                System.out.println("Описание заявки: " + item.getDescription());
                System.out.println("ID заявки: " + item.getId() + "\n");
                System.out.println();
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }

    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println("Имя заявки: " + item.getName());
                System.out.println("Описание заявки: " + item.getDescription());
                System.out.println("ID заявки: " + item.getId() + "\n");
                System.out.println();
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }
}

class EditItem implements UserAction {
    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID заявки для корректировки:");
        String name = input.ask("Введите новое имя для заявки:");
        String desc = input.ask("Введите новое описание для заявки:");
        tracker.findById(id).setName(name);
        tracker.findById(id).setDescription(desc);
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }
}
