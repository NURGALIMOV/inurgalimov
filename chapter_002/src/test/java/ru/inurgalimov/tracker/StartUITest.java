package ru.inurgalimov.tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.inurgalimov.models.*;
import ru.inurgalimov.tracker.*;

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
                is("Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator() + "1. Show all items"
                        + System.lineSeparator() + "2. Edit item" + System.lineSeparator() + "3. Delete item" + System.lineSeparator()
                        + "4. Find item by Id" + System.lineSeparator() + "5. Find items by name" + System.lineSeparator()
                        + "6. Exit Program" + System.lineSeparator() + "Name: name" + System.lineSeparator()
                        + "Description: test" + System.lineSeparator() + "ID: " + item.getId() + "\n" + System.lineSeparator()
                        + System.lineSeparator() + "Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator()
                        + "1. Show all items" + System.lineSeparator() + "2. Edit item" + System.lineSeparator()
                        + "3. Delete item" + System.lineSeparator() + "4. Find item by Id" + System.lineSeparator()
                        + "5. Find items by name" + System.lineSeparator() + "6. Exit Program" + System.lineSeparator()
                        + "Выход из программы!" + System.lineSeparator()));
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
                is("Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator()
                        + "1. Show all items" + System.lineSeparator() + "2. Edit item" + System.lineSeparator()
                        + "3. Delete item" + System.lineSeparator() + "4. Find item by Id" + System.lineSeparator()
                        + "5. Find items by name" + System.lineSeparator() + "6. Exit Program" + System.lineSeparator()
                        + "Имя заявки: name" + System.lineSeparator() + "Описание заявки: test" + System.lineSeparator()
                        + "ID заявки: " + item.getId() + "\n" + System.lineSeparator() + System.lineSeparator()
                        + "Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator()
                        + "1. Show all items" + System.lineSeparator() + "2. Edit item" + System.lineSeparator()
                        + "3. Delete item" + System.lineSeparator() + "4. Find item by Id" + System.lineSeparator()
                        + "5. Find items by name" + System.lineSeparator() + "6. Exit Program" + System.lineSeparator()
                        + "Выход из программы!" + System.lineSeparator()));
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
                is("Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator()
                        + "1. Show all items" + System.lineSeparator() + "2. Edit item" + System.lineSeparator()
                        + "3. Delete item" + System.lineSeparator() + "4. Find item by Id" + System.lineSeparator()
                        + "5. Find items by name" + System.lineSeparator() + "6. Exit Program" + System.lineSeparator()
                        + "Имя заявки: name" + System.lineSeparator() + "Описание заявки: test" + System.lineSeparator()
                        + "ID заявки: " + item.getId() + "\n" + System.lineSeparator() + System.lineSeparator()
                        + "Меню." + System.lineSeparator() + "0. Add new Item" + System.lineSeparator()
                        + "1. Show all items" + System.lineSeparator() + "2. Edit item" + System.lineSeparator()
                        + "3. Delete item" + System.lineSeparator() + "4. Find item by Id" + System.lineSeparator()
                        + "5. Find items by name" + System.lineSeparator() + "6. Exit Program" + System.lineSeparator()
                        + "Выход из программы!" + System.lineSeparator()));
        System.setOut(stdout);
    }
}