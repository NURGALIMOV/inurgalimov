package ru.inurgalimov.test;

import java.io.IOException;

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
    public static void main(String[] args) {
        FileSearch fileSearch = new FileSearch("C:\\Хатын\\резида", "1.txt", "-m", "C:\\soft\\log.txt");
        try {
            fileSearch.writeLog(fileSearch.search());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
