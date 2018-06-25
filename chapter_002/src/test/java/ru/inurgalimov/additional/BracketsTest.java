package ru.inurgalimov.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BracketsTest {
    @Test
    public void whenTextValidation() {
        assertThat(Brackets.printBrackets("a[b{c}d]e"), is("[{}]"));
    }

    @Test
    public void whenTextNoValidation() {
        assertThat(Brackets.printBrackets("a[b{c}d)e}"), is("Строка не валидна!"));
    }
}
