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
                is("Меню.\r\n0. Add new Item\r\n1. Show all items\r\n2. Edit item\r\n3. Delete item\r\n"
                        + "4. Find item by Id\r\n5. Find items by name\r\n6. Exit Program\r\nИмя заявки: name\r\n"
                        + "Описание заявки: test\r\nID заявки: " + item.getId() + "\n\r\n\r\nМеню.\r\n0. Add new "
                        + "Item\r\n1. Show all items\r\n2. Edit item\r\n3. Delete item\r\n4. Find item by Id\r\n"
                        + "5. Find items by name\r\n6. Exit Program\r\nВыход из программы!\n"));
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
                is("Меню.\r\n0. Add new Item\r\n1. Show all items\r\n2. Edit item\r\n3. Delete item\r\n"
                        + "4. Find item by Id\r\n5. Find items by name\r\n6. Exit Program\r\nИмя заявки: name\r\n"
                        + "Описание заявки: test\r\nID заявки: " + item.getId() + "\n\r\n\r\nМеню.\r\n0. Add new "
                        + "Item\r\n1. Show all items\r\n2. Edit item\r\n3. Delete item\r\n4. Find item by Id\r\n"
                        + "5. Find items by name\r\n6. Exit Program\r\nВыход из программы!\n"));
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
                is("Меню.\r\n0. Add new Item\r\n1. Show all items\r\n2. Edit item\r\n3. Delete item\r\n"
                        + "4. Find item by Id\r\n5. Find items by name\r\n6. Exit Program\r\nИмя заявки: name\r\n"
                        + "Описание заявки: test\r\nID заявки: " + item.getId() + "\n\r\n\r\nМеню.\r\n0. Add new "
                        + "Item\r\n1. Show all items\r\n2. Edit item\r\n3. Delete item\r\n4. Find item by Id\r\n"
                        + "5. Find items by name\r\n6. Exit Program\r\nВыход из программы!\r\n"));
        System.setOut(stdout);
    }

}
