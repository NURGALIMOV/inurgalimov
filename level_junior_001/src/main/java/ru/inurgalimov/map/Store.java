package ru.inurgalimov.map;

import javax.sound.midi.MidiDevice;
import javax.sound.sampled.Line;
import java.util.List;
import java.util.Queue;

/*
    метод должен возвращать статистику об изменении коллекции.
    Сколько добавлено новых.
    Сколько изменено. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
    Сколько удалено.
 */
class Store {
    static class User {
        int id;
        String name;

    }
}


