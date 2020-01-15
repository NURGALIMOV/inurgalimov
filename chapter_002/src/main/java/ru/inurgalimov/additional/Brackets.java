package ru.inurgalimov.additional;

import java.util.ArrayList;
import java.util.List;

public class Brackets {
    private static final char R1 = '(';
    private static final char R2 = ')';
    private static final char S1 = '[';
    private static final char S2 = ']';
    private static final char B1 = '{';
    private static final char B2 = '}';

    public List<Character> toParseTheBrackets(String line) {
        List<Character> resultList = new ArrayList<>();
        if (validationTest(line)) {
            for (Character ch : printBrackets(line).toCharArray()) {
                resultList.add(ch);
            }
        }
        return resultList;
    }

    private boolean validationTest(String text) {
        boolean result = true;
        char[] charArray = text.toCharArray();
        char[] lastBrackets = new char[charArray.length];
        for (int i = 0, k = 0; i < charArray.length; i++) {
            if (charArray[i] == R1 || charArray[i] == S1 || charArray[i] == B1) {
                lastBrackets[k++] = charArray[i];
                continue;
            } else if ((charArray[i] == R2 && lastBrackets[--k] != R1)
                    || (charArray[i] == S2 && lastBrackets[--k] != S1)
                    || (charArray[i] == B2 && lastBrackets[--k] != B1)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private String printBrackets(String text) {
        if (validationTest(text)) {
            char[] charArray = text.toCharArray();
            String result = "";
            for (char ch : charArray) {
                if (ch == R1 || ch == S1 || ch == B1 || ch == R2 || ch == S2 || ch == B2) {
                    result = result + ch;
                }
            }
            return result;
        } else {
            return "";
        }
    }
}
