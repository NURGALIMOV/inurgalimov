package ru.inurgalimov.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;


    public StoreSQL(Config config) {
        this.config = config;

    }

    public void initDB(String name) {
        String temp = this.getClass().getResource("\\").getFile();
        String path = temp.substring(0, temp.indexOf("target")) + "src/main/resources/" + name;
        try {
            this.connect = DriverManager.getConnection(
                    this.config.get("url") + path,
                    this.config.get("username"),
                    this.config.get("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initTable() {
        try (Statement st = this.connect.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS entry (field INTEGER PRIMARY KEY NOT NULL);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleanTable() {
        try (Statement st = this.connect.createStatement()) {
            st.execute("DELETE FROM entry;"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generate(int size) {
        this.initTable();
        this.cleanTable();
        try (PreparedStatement pst = this.connect.prepareStatement("INSERT INTO entry(field) VALUES (?)")) {
            for (int i = 1; i <= size; i++) {
                pst.setInt(1, i);
                pst.addBatch();
            }
            pst.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
        List<Entry> list = new ArrayList<>();
        try (Statement st = this.connect.createStatement()) {
            ResultSet rst = st.executeQuery("SELECT * FROM entry;");
            while (rst.next()) {
                list.add(new Entry(rst.getInt("field")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

    @XmlRootElement
    public static class Entries {
        private List<Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> entry) {
            this.entry = entry;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }
}
