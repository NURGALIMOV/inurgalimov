package ru.inurgalimov.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {

    @Test
    public void whenWeSendAMessageWithOneKey() throws Exception {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Test");
        String actual = sg.generate("Hello, ${name}!", map);
        String expected = "Hello, Test!";
        assertThat(actual, is(expected));
    }

    @Test(expected = Exception.class)
    public void whenMapKeyIsEmpty() throws Exception {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        String actual = sg.generate("Hello, ${name}!", map);
    }

    @Test(expected = Exception.class)
    public void whenMapIsMoreThanRequired() throws Exception {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Test");
        map.put("test", "test");
        String actual = sg.generate("Hello, ${name}!", map);
    }

    @Test
    public void whenWeSendAMessageWithTwoKey() throws Exception {
        SimpleGenerator sg = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Test");
        map.put("test", "test");
        String actual = sg.generate("Hello, ${name} ${test}!", map);
        String expected = "Hello, Test test!";
        assertThat(actual, is(expected));
    }
}