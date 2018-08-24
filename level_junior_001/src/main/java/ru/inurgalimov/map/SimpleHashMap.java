package ru.inurgalimov.map;


import java.util.Iterator;

public class SimpleHashMap<K, V> implements Iterable {
    private SimpleEntry<K, V>[] simpleEntries;
    private int count = 0;

    public SimpleHashMap() {
        this.simpleEntries = new SimpleEntry[1000];
    }

    public SimpleHashMap(int size) {
        this.simpleEntries = new SimpleEntry[size];
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (count == simpleEntries.length) {
            resize();
        }
        int index = hash(key);
        if (simpleEntries[index] == null) {
            simpleEntries[index] = new SimpleEntry<>(key, value);
            result = true;
            count++;
        } else if (simpleEntries[index] != null && simpleEntries[index].getKeyEntry().equals(key)) {
            simpleEntries[index] = new SimpleEntry<>(key, value);
            result = true;
            count++;
        }
        return result;
    }

    public V get(K key) {
        int index = hash(key);
        return (simpleEntries[index] != null && simpleEntries[index].getKeyEntry().equals(key))
                ? simpleEntries[index].getValueEntry() : null;
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = hash(key);
        if (simpleEntries[index] != null && simpleEntries[index].getKeyEntry().equals(key)) {
            simpleEntries[index] = null;
            result = true;
        }
        return result;
    }

    public int hash(K key) {
        return Math.abs(((key.hashCode() >> 2) * 31) % simpleEntries.length);
    }

    public void resize() {
        count = 0;
        SimpleEntry<K, V>[] temp = simpleEntries;
        simpleEntries = new SimpleEntry[simpleEntries.length * 2];
        for (SimpleEntry se : temp) {
            if (se != null) {
                insert((K) se.getKeyEntry(), (V) se.getValueEntry());
            }
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (; index < simpleEntries.length; index++) {
                    if (simpleEntries[index] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public SimpleEntry next() {
                SimpleEntry stepIterator = null;
                if (hasNext()) {
                    stepIterator = simpleEntries[index];
                    index++;
                }
                return stepIterator;
            }
        };
    }

    public class SimpleEntry<K, V> {
        private K keyEntry;
        private V valueEntry;

        public SimpleEntry(K keyEntry, V valueEntry) {
            this.keyEntry = keyEntry;
            this.valueEntry = valueEntry;
        }

        public K getKeyEntry() {
            return keyEntry;
        }

        public V getValueEntry() {
            return valueEntry;
        }

        public void setValueEntry(V valueEntry) {
            this.valueEntry = valueEntry;
        }
    }

}
