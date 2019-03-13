package ru.inurgalimov.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
public class FileSearch {
    private File file;
    private String fileName;
    private String searchRule;
    private File result;

    public FileSearch(String directory, String fileName, String searchRule, String result) {
        this.file = new File(directory);
        this.fileName = fileName;
        this.searchRule = searchRule;
        this.result = new File(result);
    }

    protected File[] search() {
        File[] listFile = file.listFiles((a, b) -> {
            boolean check = false;
            for (File f : a.listFiles()) {
                if(f.getName().contains(b)) {
                    check = true;
                }
            }
            return check;
        });
        return listFile;
    }

    protected void writeLog(File[] files) throws IOException {
        FileOutputStream fos = new FileOutputStream(result);
        for (File f : files) {
            fos.write((f.getAbsolutePath() + "\n").getBytes());
        }
    }
}
