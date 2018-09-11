package ru.inurgalimov.additional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreTest {
    @Test
    public void whenAddInList() {
        Store store = new Store();
        List<Store.User> listPrevioues = new ArrayList<>();
        listPrevioues.add(new Store.User(1, "a"));
        listPrevioues.add(new Store.User(2, "b"));
        listPrevioues.add(new Store.User(3, "c"));
        List<Store.User> listForTest = new ArrayList<>();
        listForTest.addAll(listPrevioues);
        listForTest.add(new Store.User(4, "d"));
        Info info = store.diff(listPrevioues, listForTest);
        assertThat(info.getCountAdd(), is(1));
        assertThat(info.getCountRemove(), is(0));
        assertThat(info.getCountMod(), is(0));
    }

    @Test
    public void whenRemoveFromList() {
        Store store = new Store();
        List<Store.User> listPrevioues = new ArrayList<>();
        listPrevioues.add(new Store.User(1, "a"));
        listPrevioues.add(new Store.User(2, "b"));
        listPrevioues.add(new Store.User(3, "c"));
        List<Store.User> listForTest = new ArrayList<>();
        listForTest.addAll(listPrevioues);
        listForTest.remove(1);
        Info info = store.diff(listPrevioues, listForTest);
        assertThat(info.getCountAdd(), is(0));
        assertThat(info.getCountRemove(), is(1));
        assertThat(info.getCountMod(), is(0));
    }

    @Test
    public void whenModElementInList() {
        Store store = new Store();
        List<Store.User> listPrevioues = new ArrayList<>();
        listPrevioues.add(new Store.User(1, "a"));
        listPrevioues.add(new Store.User(2, "b"));
        listPrevioues.add(new Store.User(3, "c"));
        List<Store.User> listForTest = new ArrayList<>();
        listForTest.addAll(listPrevioues);
        listForTest.set(1, new Store.User(2, "d"));
        Info info = store.diff(listPrevioues, listForTest);
        assertThat(info.getCountAdd(), is(0));
        assertThat(info.getCountRemove(), is(0));
        assertThat(info.getCountMod(), is(1));
    }
}