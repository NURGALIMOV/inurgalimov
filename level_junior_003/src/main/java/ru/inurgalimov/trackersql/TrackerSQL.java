package ru.inurgalimov.trackersql;

import ru.inurgalimov.models.Item;
import ru.inurgalimov.tracker.ITracker;
import ru.inurgalimov.tracker.Tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.BiFunction;

/**
 * TrackerSQL
 * @author Ilshat Nurgalimov
 * @version 12.04.2019
 */

public class TrackerSQL implements ITracker, AutoCloseable {
    private final Connection connection;
    private boolean state = false;

    public TrackerSQL() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public boolean init() {
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement pst = connection.prepareStatement("INSERT INTO item (id, name, description, creates)"
                + "VALUES (?, ?, ?, ?)")) {
            pst.setString(1, item.getId());
            pst.setString(2, item.getName());
            pst.setString(3, item.getDescription());
            pst.setLong(4, item.getCreate());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        item.setId(id);
        try (PreparedStatement pst = connection.prepareStatement("UPDATE item AS i "
                + "SET name = ?, description = ?, creates = ? WHERE i.id = id")) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.setLong(3, item.getCreate());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (PreparedStatement pst = connection.prepareStatement("DELETE FROM item AS i WHERE i.id = ?")) {
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item[] findAll() {
        Item[] items = null;
        try (Statement st = connection.createStatement()) {
            ResultSet rst = st.executeQuery("SELECT * FROM item");
            items = fill(rst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item[] findByName(String key) {
        Item[] items = null;
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM item AS i WHERE i.name = ?")) {
            pst.setString(1, key);
            ResultSet rst = pst.executeQuery();
            items = fill(rst);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM item AS i WHERE i.id = ?")) {
            pst.setString(1, id);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                item = new Item(
                        rst.getString("name"),
                        rst.getString("description"),
                        rst.getLong("creates")
                );
                item.setId(rst.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public <T> T find(String k, BiFunction<List, String, T> biFunction) {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public boolean initTable() {
        try (Statement statement = connection.createStatement()) {
            this.state = statement.execute("CREATE TABLE IF NOT EXISTS item("
                    + "id VARCHAR(2000) PRIMARY KEY,"
                    + "name VARCHAR(2000),"
                    + "description VARCHAR(2000),"
                    + "creates BIGINT"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.state;
    }

    public Item[] fill(ResultSet rst) throws Exception {
        Item[] items = null;
        List<Item> list = new ArrayList<>();
        while (rst.next()) {
            Item item = new Item();
            item = new Item(
                    rst.getString("name"),
                    rst.getString("description"),
                    rst.getLong("creates")
            );
            item.setId(rst.getString("id"));
            list.add(item);
        }
        items = new Item[list.size()];
        list.toArray(items);
        return items;
    }
}
