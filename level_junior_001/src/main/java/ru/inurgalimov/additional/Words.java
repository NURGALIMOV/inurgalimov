package ru.inurgalimov.additional;

import java.util.Map;
import java.util.TreeMap;

/**
 * Additional task.
 * Two words are given. The task is to check that they consist of the same symbols.
 *
 * @author Ilshat Nurgalimov
 * @version 1.0
 */
public class Words {
    /**
     * The first variant of solving this task, the complexity O(n^2).
     *
     * @param word      - first word for verification
     * @param checkWord - second word for verification
     * @return boolean
     */
    public boolean checkWordsFirst(String word, String checkWord) {
        boolean resultCheck = false;
        char[] wordArray = word.toCharArray();
        char[] checkWordArray = checkWord.toCharArray();
        if (wordArray.length == checkWordArray.length) {
            for (char w : wordArray) {
                for (char c : checkWordArray) {
                    if (w == c) {
                        resultCheck = true;
                    }
                }
                if (!resultCheck) {
                    break;
                }
            }
        }
        return resultCheck;
    }
    /**
     * The second variant of solving this task, the complexity O(2*n) = O(n).
     *
     * @param word      - first word for verification
     * @param checkWord - second word for verification
     * @return boolean
     */
    public boolean checkWordsSecond(String word, String checkWord) {
        boolean resultCheck = false;
        char[] wordArray = word.toCharArray();
        char[] checkWordArray = checkWord.toCharArray();
        if (wordArray.length == checkWordArray.length) {
            int i = 0;
            int j = 0;
            for (char w : wordArray) {
                i = i + w;
            }
            for (char c : checkWordArray) {
                j = j + c;
            }
            if (i == j) {
                resultCheck = true;
            }
        }
        return resultCheck;
    }

    /**
     * The second variant of solving this task, the complexity O(2*n) = O(n).
     *
     * @param word      - first word for verification
     * @param checkWord - second word for verification
     * @return boolean
     */
    public boolean checkWordsThird(String word, String checkWord) {
        return getMap(word).equals(getMap(checkWord));
    }
    public Map<Character, Integer> getMap(String s) {
        Map<Character, Integer> out = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (out.containsKey(s.charAt(i))) {
                out.put(s.charAt(i), s.charAt(i) + 1);
            } else {
                out.put(s.charAt(i), 1);
            }
        }
        return out;
    }
}
