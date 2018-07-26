package ru.inurgalimov.additional;

import java.util.ArrayList;
import java.util.List;

public class Brackets {
    private final char r1 = '(';
    private final char r2 = ')';
    private final char s1 = '[';
    private final char s2 = ']';
    private final char b1 = '{';
    private final char b2 = '}';

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
            if (charArray[i] == r1 || charArray[i] == s1 || charArray[i] == b1) {
                lastBrackets[k++] = charArray[i];
                continue;
            } else if ((charArray[i] == r2 && lastBrackets[--k] != r1)
                    || (charArray[i] == s2 && lastBrackets[--k] != s1)
                    || (charArray[i] == b2 && lastBrackets[--k] != b1)) {
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
                if (ch == r1 || ch == s1 || ch == b1 || ch == r2 || ch == s2 || ch == b2) {
                    result = result + ch;
                }
            }
            return result;
        } else {
            return "";
        }
    }
}
