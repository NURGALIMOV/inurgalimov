package ru.inurgalimov.additional;

import org.junit.Test;

import java.util.*;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BracketsTest {
    @Test
    public void whenTextValidation() {
        Brackets test = new Brackets();
        List<Character> testList = new ArrayList<>();
        testList.add('[');
        testList.add('{');
        testList.add('}');
        testList.add(']');
        assertThat(test.toParseTheBrackets("a[b{c}d]e"), is(testList));
    }

    @Test
    public void whenTextNoValidation() {
        Brackets test = new Brackets();
        assertThat(test.toParseTheBrackets("a[b{c}d)e}"), is(new ArrayList<Character>()));
    }
}
