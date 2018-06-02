package ru.inurgalimov.tracker;

import org.junit.Test;
import org.junit.Assert;
import ru.inurgalimov.models.Item;

import static org.hamcrest.core.Is.*;


public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        Assert.assertThat(tracker.findAll()[0].getName(), is("test name"));
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
}
