package ru.inurgalimov.tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.inurgalimov.models.Item;

import static org.hamcrest.core.Is.is;


public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Assert.assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceOldItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.findAll()[1].setId("test");
        tracker.replace("test", item3);

        Assert.assertThat(tracker.findAll()[1], is(item3));
    }

    @Test
    public void whenDeleteOldItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.findAll()[1].setId("test");
        tracker.delete("test");

        Assert.assertThat(tracker.findAll()[1], is(item3));
    }

    @Test
    public void whenFindAllItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        Item item[] = {item1, item2, item3};
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Assert.assertThat(tracker.findAll(), is(item));
    }

    @Test
    public void whenFindByNameItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test1", "testDescription3", 12345L);
        Item item[] = {item1, item3};
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);

        Assert.assertThat(tracker.findByName("test1"), is(item));
    }

    @Test
    public void whenFindByIdItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.findAll()[1].setId("tester");

        Assert.assertThat(tracker.findById("tester"), is(item2));
    }
}
