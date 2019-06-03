package ru.inurgalimov.tdd;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Генератор сообщений.
 *
 * @author Ilshat Nurgalimov
 * @since 03.06.2019
 */
public class SimpleGenerator {
    /**
     * Регулярное выражение для преобразования сообщения.
     */
    private static final Pattern KEYS = Pattern.compile("\\$.+?}");

    /**
     * Преобразуем сообщение.
     *
     * @param message - исходное сообщение.
     * @param map     - список ключей и необходимоых сообщений для замены.
     * @return - возвращаем преобразованное сообщение.
     * @throws Exception - могут возникать исключения при не соответсвии списка ключей требуемым.
     */
    public String generate(String message, Map<String, String> map) throws Exception {
        Matcher matcher = KEYS.matcher(message);
        Set<String> key = new HashSet<>();
        StringBuffer result = new StringBuffer();
        String temp;
        boolean validate = map.isEmpty();

        if (!validate) {
            while (matcher.find()) {
                temp = message.substring(matcher.start() + 2, matcher.end() - 1);
                key.add(temp);
                temp = map.get(temp);
                if (temp == null) {
                    validate = true;
                    break;
                }
                matcher.appendReplacement(result, temp);
            }
            matcher.appendTail(result);
        }

        if (validate || key.size() != map.size()) {
            throw new Exception("Количество ключей не совпадает с неоходимым количеством!");
        }

        return result.toString();
    }
}
