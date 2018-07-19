package ru.inurgalimov.tracker;

import org.junit.Test;
import org.junit.Assert;
import ru.inurgalimov.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.*;


public class StartUITest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        Assert.assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenShowAllItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "test"));
        Input input = new StubInput(new String[]{"1", "6"});
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        Assert.assertThat(
                new String(out.toByteArray()),
                is("Меню.\\n0. Add new Item\\n1. Show all items\\n2. Edit item\\n3. Delete item\\n4. Find item by Id\\n5. Find items by name\\n6. Exit Program\\nИмя заявки: name\\nОписание заявки: test\\nID заявки: -1483377011838690965\\n\\n\\nМеню.\\n0. Add new Item\\n1. Show all items\\n2. Edit item\\n3. Delete item\\n4. Find item by Id\\n5. Find items by name\\n6. Exit Program\\nВыход из программы!\\n"));
        System.setOut(stdout);
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        Assert.assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenTrackerDeleteValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Item test = null;
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        Assert.assertThat(tracker.findById(item.getId()), is(test));
    }

    @Test
    public void whenFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "test"));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        Assert.assertThat(
                new String(out.toByteArray()),
                is("Меню.\\n0. Add new Item\\n1. Show all items\\n2. Edit item\\n3. Delete item\\n4. Find item by Id\\n5. Find items by name\\n6. Exit Program\\nИмя заявки: name\\nОписание заявки: test\\nID заявки: -8560323645504024004\\n\\n\\nМеню.\\n0. Add new Item\\n1. Show all items\\n2. Edit item\\n3. Delete item\\n4. Find item by Id\\n5. Find items by name\\n6. Exit Program\\nВыход из программы!\\n"));
        System.setOut(stdout);
    }

    @Test
    public void whenFindItemsByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("name", "test"));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        System.setOut(new PrintStream(out));
        new StartUI(input, tracker).init();
        Assert.assertThat(
                new String(out.toByteArray()),
                is("Меню.\\n0. Add new Item\\n1. Show all items\\n2. Edit item\\n3. Delete item\\n4. Find item by Id\\n5. Find items by name\\n6. Exit Program\\nИмя заявки: name\\nОписание заявки: test\\nID заявки: -4155689404283036092\\n\\n\\nМеню.\\n0. Add new Item\\n1. Show all items\\n2. Edit item\\n3. Delete item\\n4. Find item by Id\\n5. Find items by name\\n6. Exit Program\\nВыход из программы!\\n"));
        System.setOut(stdout);
    }

}
