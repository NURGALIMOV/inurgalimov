package ru.inurgalimov.trackersql;

import org.junit.Test;
import ru.inurgalimov.models.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkAdd() throws Exception {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkReplace() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}