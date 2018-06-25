package ru.inurgalimov.additional;

public class Brackets {
    private static final char ROUND1 = '(';
    private static final char ROUND2 = ')';
    private static final char SQUARE1 = '[';
    private static final char SQUARE2 = ']';
    private static final char BRACES1 = '{';
    private static final char BRACES2 = '}';

    public static boolean validationTest(String text) {
        boolean result = true;
        char[] charArray = text.toCharArray();
        char[] lastBrackets = new char[charArray.length];
        for (int i = 0, k = 0; i < charArray.length; i++) {
            if (charArray[i] == ROUND1 || charArray[i] == SQUARE1 || charArray[i] == BRACES1) {
                lastBrackets[k++] = charArray[i];
                continue;
            } else if ((charArray[i] == ROUND2 && lastBrackets[--k] != ROUND1)
                    || (charArray[i] == SQUARE2 && lastBrackets[--k] != SQUARE1)
                    || (charArray[i] == BRACES2 && lastBrackets[--k] != BRACES1)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static String printBrackets(String text) {
        if (validationTest(text)) {
            char[] charArray = text.toCharArray();
            String result = "";
            for (char ch : charArray) {
                if (ch == ROUND1 || ch == SQUARE1 || ch == BRACES1 || ch == ROUND2 || ch == SQUARE2 || ch == BRACES2) {
                    result = result + ch;
                }
            }
            return result;
        } else {
            return "Строка не валидна!";
        }
    }
}
