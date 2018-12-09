package ru.inurgalimov.tracker;

import org.junit.Assert;
import org.junit.Test;
import ru.inurgalimov.models.Item;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerVersionFourTest {
    TrackerVersionFour tracker = TrackerVersionFour.TRACKER_VERSION_FOUR;

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Object[] o = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        Item it = (Item) o[0];
        it.setId("test");
        Assert.assertThat(it, is(item));
        Object[] o1 = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        for (int i = 0; i < o1.length; i++) {
            Item itm = (Item) o1[i];
            itm.setId("test" + i);
        }
        tracker.delete("test0");
    }

    @Test
    public void whenReplaceOldItemThenTrackerHasSameItem() {
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        Object[] o = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        Item it = (Item) o[1];
        it.setId("test");
        tracker.replace("test", item3);
        Object[] o1 = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        it = (Item) o1[1];
        Assert.assertThat(it, is(item3));
        Object[] o2 = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        for (int i = 0; i < o1.length; i++) {
            Item itm = (Item) o1[i];
            itm.setId("test" + i);
        }
        tracker.delete("test0");
        tracker.delete("test1");
        tracker.delete("test2");

    }

    @Test
    public void whenDeleteOldItemThenTrackerHasSameItem() {
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Object[] o1 = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        for (int i = 0; i < o1.length; i++) {
            Item itm = (Item) o1[i];
            itm.setId("test" + i);
        }
        tracker.delete("test1");
        Object[] o2 = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        Item it = (Item) o2[1];
        Assert.assertThat(it, is(item3));
        tracker.delete("test0");
        tracker.delete("test1");
        tracker.delete("test2");
    }

    @Test
    public void whenFindAllItemThenTrackerHasSameItem() {
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Object[] o = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        for (int i = 0; i < o.length; i++) {
            Item itm = (Item) o[i];
            itm.setId("test" + i);
        }
        Assert.assertThat((Item) o[0], is(item1));
        Assert.assertThat((Item) o[1], is(item2));
        Assert.assertThat((Item) o[2], is(item3));
        tracker.delete("test0");
        tracker.delete("test1");
        tracker.delete("test2");
    }

    @Test
    public void whenFindByNameItemThenTrackerHasSameItem() {
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test1", "testDescription3", 12345L);
        item1.setId("test1");
        item2.setId("test2");
        item3.setId("test3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Item[] it = tracker.find("test1", (a, b) -> {
            List<Item> temp = new ArrayList<>();
            for (Object o : a) {
                Item item = (Item) o;
                if (item.getName().equals(b)) {
                    temp.add(item);
                }
            }
            return temp.toArray(new Item[temp.size()]);
        });

        Assert.assertThat(it[0], is(item1));
        Assert.assertThat(it[1], is(item3));
        Object[] o = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        for (int i = 0; i < o.length; i++) {
            Item itm = (Item) o[i];
            itm.setId("test" + i);
        }
        tracker.delete("test0");
        tracker.delete("test1");
        tracker.delete("test2");
    }

    @Test
    public void whenFindByIdItemThenTrackerHasSameItem() {
        Item item1 = new Item("test1", "testDescription1", 123L);
        Item item2 = new Item("test2", "testDescription2", 1234L);
        Item item3 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        Object[] o = tracker.find("", (a, b) -> a.toArray(new Item[a.size()]));
        for (int i = 0; i < o.length; i++) {
            Item itm = (Item) o[i];
            itm.setId("test" + i);
        }
        Item it = (Item) o[1];
        it.setId("1");
        Assert.assertThat(tracker.findById("1"), is(item2));
        tracker.delete("test0");
        tracker.delete("test1");
        tracker.delete("test2");
        tracker.delete("1");
    }

}