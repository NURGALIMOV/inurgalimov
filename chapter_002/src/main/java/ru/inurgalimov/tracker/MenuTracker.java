package ru.inurgalimov.tracker;

import ru.inurgalimov.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void showMenu(Consumer<List<UserAction>> consumer) {
        consumer.accept(this.actions);
    }

    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new Item"));
        this.actions.add(new MenuTracker.ShowItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindId(4, "Find item by Id"));
        this.actions.add(new FindName(5, "Find items by name"));
        this.actions.add(new ExitMenu(6, "Exit Program"));
    }

    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public int[] rangeArray() {
        int[] range = new int[this.actions.size()];
        for (int i = 0; i < this.actions.size(); i++) {
            range[i] = this.actions.get(i).key();
        }
        return range;
    }


    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите, имя заявки:");
            String desc = input.ask("Введите, описание заявки:");
            tracker.add(new Item(name, desc));
        }
    }

    private class ExitMenu extends BaseAction {
        public ExitMenu(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
        }
    }

    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID заявки для удаления:");
            if (tracker.findById(id) != null) {
                tracker.delete(id);
            } else {
                System.out.println("Такой заявки нет.");
            }
        }
    }

    private class FindId extends BaseAction {
        public FindId(int key, String name) {
            super(key, name);
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
    }

    private class FindName extends BaseAction {
        public FindName(int key, String name) {
            super(key, name);
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
    }

    private static class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println("Name: " + item.getName());
                System.out.println("Description: " + item.getDescription());
                System.out.println("ID: " + item.getId() + "\n");
                System.out.println();
            }
        }
    }
}

class EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        Item editItem = tracker.findById(input.ask("Введите ID заявки для корректировки:"));
        if (editItem != null) {
            editItem.setName(input.ask("Введите новое имя для заявки:"));
            editItem.setDescription(input.ask("Введите новое описание для заявки:"));
        } else {
            System.out.println("Такой заявки нет в списке.");
        }
    }
}
