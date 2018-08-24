package ru.inurgalimov.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test
    public void whereInsertInMap() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("Ilshat", "Junior Java Developer");
        simpleHashMap.insert("Ilshat", "Java Developer");
        assertThat(simpleHashMap.get("Ilshat"), is("Java Developer"));
    }

    @Test
    public void whereResizeMap() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>(3);
        simpleHashMap.insert("Ilshat", "Junior Java Developer");
        simpleHashMap.insert("Andrew", "Java Developer");
        simpleHashMap.insert("Petr", "Senior Java Developer");
        simpleHashMap.insert("Vladimir", "Java Developer");
        assertThat(simpleHashMap.get("Andrew"), is("Java Developer"));
        assertThat(simpleHashMap.get("Ilshat"), is("Junior Java Developer"));
        assertThat(simpleHashMap.get("Petr"), is("Senior Java Developer"));
        assertThat(simpleHashMap.get("Vladimir"), is("Java Developer"));
    }

    @Test
    public void whereUseIteratorMap() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("Ilshat", "Junior Java Developer");
        simpleHashMap.insert("Andrew", "Java Developer");
        simpleHashMap.insert("Petr", "Senior Java Developer");
        Iterator it = simpleHashMap.iterator();
        assertThat(it.hasNext(), is(true));
        SimpleHashMap.SimpleEntry temp = (SimpleHashMap.SimpleEntry) it.next();
        assertThat(simpleHashMap.get((String) temp.getKeyEntry()), is(temp.getValueEntry()));
        assertThat(it.hasNext(), is(true));
        temp = (SimpleHashMap.SimpleEntry) it.next();
        assertThat(simpleHashMap.get((String) temp.getKeyEntry()), is(temp.getValueEntry()));
        assertThat(it.hasNext(), is(true));
        temp = (SimpleHashMap.SimpleEntry) it.next();
        assertThat(simpleHashMap.get((String) temp.getKeyEntry()), is(temp.getValueEntry()));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whereDeleteElementInMap() {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert("Ilshat", "Junior Java Developer");
        simpleHashMap.insert("Andrew", "Java Developer");
        simpleHashMap.insert("Petr", "Senior Java Developer");
        assertThat(simpleHashMap.get("Ilshat"), is("Junior Java Developer"));
        assertThat(simpleHashMap.delete("Ilshat"), is(true));
        String s = null;
        assertThat(simpleHashMap.get("Ilshat"), is(s));
    }


}