package ru.inurgalimov.crud.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.inurgalimov.crud.model.Role;
import ru.inurgalimov.crud.model.User;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Хранилище пользователей в базе данных.
 */
public class DBStore implements Store {

    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(DBStore.class.getName());

    private static final UUID ADMIN_ID = UUID.fromString("d66541c4-a9db-3308-8c67-bbf87dc0df8b");

    /**
     * Пул коннектов.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();

    /**
     * Единственный экземпляр класса.
     */
    private static final DBStore INSTANCE = new DBStore();

    /**
     * Конструктор.
     */
    private DBStore() {
        init();
        User admin = new User("admin", "admin", "admin@admin.ru", ADMIN_ID);
        admin.setRole(Role.ADMINISTRATOR);
        admin.setPassword("admin");
        if (findById(admin) == null) {
            add(admin);
        }
    }

    /**
     * Инициализация.
     */
    private void init() {
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties config = new Properties();
            config.load(in);
            SOURCE.setDriverClassName(config.getProperty("driver-class-name"));
            SOURCE.setUrl(config.getProperty("url"));
            SOURCE.setUsername(config.getProperty("username"));
            SOURCE.setPassword(config.getProperty("password"));
            SOURCE.setMinIdle(5);
            SOURCE.setMaxIdle(10);
            SOURCE.setMaxOpenPreparedStatements(100);
        } catch (Exception e) {
            LOGGER.error("Ошибка инициализации.", e);
        }
    }

    /**
     * Возвращает единственный экземпляр класса.
     *
     * @return экземпляр класса.
     */
    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "INSERT INTO users (id, name, login, email, photoId, creates, role, password, country, city) "
                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pst.setString(1, user.getId().toString());
            pst.setString(2, user.getName());
            pst.setString(3, user.getLogin());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPhotoId());
            pst.setLong(6, user.getCreateDate().getTime());
            pst.setString(7, user.getRole().getRoleName());
            pst.setString(8, user.getPassword());
            pst.setString(9, user.getCountry());
            pst.setString(10, user.getCity());
            pst.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Ошибка записи данных в БД.", e);
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        User old = findById(user);
        if (old != null) {
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement pst = connection.prepareStatement("UPDATE users AS i "
                         + "SET name = ?, login = ?, email = ?, role = ?, password = ?, country = ?, city = ? "
                         + "WHERE i.id = ?")) {
                String newName = user.getName();
                String oldName = old.getName();
                pst.setString(1, ((newName != null) && !oldName.equals(newName)) ? newName : oldName);

                String newLogin = user.getLogin();
                String oldLogin = old.getLogin();
                pst.setString(2, ((newLogin != null) && !oldLogin.equals(newLogin)) ? newLogin : oldLogin);

                String newEmail = user.getEmail();
                String oldEmail = old.getEmail();
                pst.setString(3, ((newEmail != null) && !oldEmail.equals(newEmail)) ? newEmail : oldEmail);

                String newRole = user.getRole().getRoleName();
                String oldRole = old.getRole().getRoleName();
                pst.setString(4, ((newRole != null) && !oldRole.equals(newRole)) ? newRole : oldRole);

                String newPassword = user.getPassword();
                String oldPassword = old.getPassword();
                pst.setString(5, ((newPassword != null) && !oldPassword.equals(newPassword))
                        ? newPassword : oldPassword);

                String newCountry  = user.getCountry();
                String oldCountry = old.getCountry();
                pst.setString(6, ((newCountry != null) && !oldCountry.equals(newCountry))
                        ? newCountry : oldCountry);

                String newCity = user.getCity();
                String oldCity = old.getCity();
                pst.setString(7, ((newCity != null) && !oldCity.equals(newCity))
                        ? newCity : oldCity);

                pst.setString(8, user.getId().toString());
                pst.executeUpdate();
                result = true;
            } catch (Exception e) {
                LOGGER.error("Ошибка при обновлении данных пользователя.", e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = true;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("DELETE FROM users AS i WHERE i.id = ?")) {
            pst.setString(1, user.getId().toString());
            pst.executeUpdate();
            result = findById(user) == null;
        } catch (Exception e) {
            LOGGER.error("Ошибка удаления данных из БД.", e);
        }
        return result;
    }

    @Override
    public Collection<User> findAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rst = st.executeQuery("SELECT * FROM users");
            while (rst.next()) {
                User user = new User(
                        rst.getString("name"),
                        rst.getString("login"),
                        rst.getString("email"),
                        UUID.fromString(rst.getString("id")));
                user.setPhotoId(rst.getString("photoId"));
                user.getCreateDate().setTime(rst.getLong("creates"));
                user.setRole(Arrays.stream(Role.values()).filter(role -> {
                    try {
                        return role.getRoleName().equals(rst.getString("role"));
                    } catch (SQLException e) {
                        LOGGER.error("Ошибка при получении списка пользователей.", e);
                    }
                    return false;
                }).findFirst().orElse(Role.USER));
                user.setPassword(rst.getString("password"));
                user.setCountry(rst.getString("country"));
                user.setCity(rst.getString("city"));
                result.add(user);
            }
        } catch (Exception e) {
            LOGGER.error("Ошибка при получении списка пользователей.", e);
        }
        return result;
    }

    @Override
    public User findById(User user) {
        User result = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM users AS i WHERE i.id = ?")) {
            pst.setString(1, user.getId().toString());
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                result = new User(
                        rst.getString("name"),
                        rst.getString("login"),
                        rst.getString("email"),
                        UUID.fromString(rst.getString("id")));
                result.setPhotoId(rst.getString("photoId"));
                result.getCreateDate().setTime(rst.getLong("creates"));
                result.setRole(Arrays.stream(Role.values()).filter(role -> {
                    try {
                        return role.getRoleName().equals(rst.getString("role"));
                    } catch (SQLException e) {
                        LOGGER.error("Ошибка при поиске пользователя.", e);
                    }
                    return false;
                }).findFirst().orElse(Role.USER));
                result.setPassword(rst.getString("password"));
                result.setCountry(rst.getString("country"));
                result.setCity(rst.getString("city"));
            }
        } catch (Exception e) {
            LOGGER.error("Ошибка при поиске пользователя.", e);
        }
        return result;
    }
}
