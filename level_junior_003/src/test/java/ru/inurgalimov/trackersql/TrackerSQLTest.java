package ru.inurgalimov.trackersql;

import org.junit.Test;
import ru.inurgalimov.models.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkAdd() {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item item = new Item("test", "test", 12345);
            item.setId("test");
            sql.init();
            sql.initTable();
            sql.add(item);
            Item result = sql.findById(item.getId());
            assertThat(result.getId(), is(item.getId()));
            assertThat(result.getName(), is(item.getName()));
            assertThat(result.getDescription(), is(item.getDescription()));
            assertThat(result.getCreate(), is(item.getCreate()));
            sql.delete(item.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkReplace() {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item item1 = new Item("test1", "test1", 12345);
            Item item2 = new Item("test2", "test2", 54321);
            item1.setId("test");
            sql.init();
            sql.initTable();
            sql.add(item1);
            sql.replace(item1.getId(), item2);
            Item result = sql.findById(item1.getId());
            assertThat(result.getId(), is(item2.getId()));
            assertThat(result.getName(), is(item2.getName()));
            assertThat(result.getDescription(), is(item2.getDescription()));
            assertThat(result.getCreate(), is(item2.getCreate()));
            sql.delete(item1.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}