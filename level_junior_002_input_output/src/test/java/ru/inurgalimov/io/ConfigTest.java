package ru.inurgalimov.io;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenCheckConfig() {
        Config cfg = new Config("app.properties");
        cfg.load();

        assertThat(cfg.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(cfg.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(cfg.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(cfg.value("hibernate.connection.username"), is("postgres"));
        assertThat(cfg.value("hibernate.connection.password"), is("password"));
    }
}