package ru.inurgalimov.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * Ключи
 * -d - директория в которая начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 *
 * @author Nurgalimov Ilshat
 * @version 13.03.2019
 */
public class FileSearchStart {
    private Map<String, String> map = new HashMap<>();

    public FileSearchStart(String d, String n, String o) {
        this.map.put("-d", d);
        this.map.put("-n", n);
        this.map.put("-m", "-m");
        this.map.put("-f", "-f");
        this.map.put("-r", "-r");
        this.map.put("-o", o);
    }

    public static void main(String[] args) {
        FileSearchStart fss = new FileSearchStart(args[1], args[3], args[6]);

        try {
            FileSearch fileSearch = new FileSearch(fss.validate(args[0]), fss.validate(args[2]),
                    fss.validate(args[4]), fss.validate(args[5]));
            Files.walkFileTree(Paths.get(fss.validate("-d")), fileSearch);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String validate(String key) throws Exception {
        String result = map.get(key);
        if (result == null) {
            throw new UnsupportedOperationException("Invalid key entered");
        }
        return result;
    }
}
