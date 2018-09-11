package ru.inurgalimov.additional;

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
    Info diff(List<User> previoues, List<User> current) {
        Info info = new Info(previoues, current);
        info.countingStatistics();
        return info;
    }
    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            User user = (User) obj;
            return id == user.id;
        }
    }
}


