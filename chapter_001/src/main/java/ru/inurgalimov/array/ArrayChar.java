package ru.inurgalimov.array;

public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        // проверить. что массив data имеет первые элементы одинаковые с value
        for (int i = 0; i < value.length; i++) {
            if (value[i] != data[i]) {
                return false;
            }
        }
        return result;
    }
}
