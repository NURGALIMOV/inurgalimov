package ru.inurgalimov.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WordsTest {
    @Test
    public void whenCheckWords() {
        Words words = new Words();
        assertThat(words.checkWordsFirst("Hello", "hello"), is(false));
        assertThat(words.checkWordsFirst("hello", "olleh"), is(true));
        assertThat(words.checkWordsFirst("Мам", "Мама"), is(false));
        assertThat(words.checkWordsFirst("JAVA", "java"), is(false));
        assertThat(words.checkWordsFirst("jAvA", "jAvA"), is(true));
        assertThat(words.checkWordsFirst("Task", "Task"), is(true));
        assertThat(words.checkWordsSecond("Hello", "hello"), is(false));
        assertThat(words.checkWordsSecond("hello", "olleh"), is(true));
        assertThat(words.checkWordsSecond("Мам", "Мама"), is(false));
        assertThat(words.checkWordsSecond("JAVA", "java"), is(false));
        assertThat(words.checkWordsSecond("jAvA", "jAvA"), is(true));
        assertThat(words.checkWordsSecond("Task", "Task"), is(true));
        assertThat(words.checkWordsThird("Hello", "hello"), is(false));
        assertThat(words.checkWordsThird("hello", "olleh"), is(true));
        assertThat(words.checkWordsThird("Мам", "Мама"), is(false));
        assertThat(words.checkWordsThird("JAVA", "java"), is(false));
        assertThat(words.checkWordsThird("jAvA", "jAvA"), is(true));
        assertThat(words.checkWordsThird("Task", "Task"), is(true));

    }

}