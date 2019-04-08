package ru.inurgalimov.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class FileSearch extends SimpleFileVisitor<Path> {
    private File root;
    private String fileName;
    private String searchRule;
    private File result;

    public FileSearch(String directory, String fileName, String searchRule, String result) {
        this.root = new File(directory);
        this.fileName = fileName;
        this.searchRule = searchRule;
        this.result = new File(result);
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        File file = path.toFile();
        switch (searchRule) {
            case "-m":
                if (file.getName().contains(this.fileName)) {
                    this.writeLog(file);
                }
                break;
            case "-f":
                if (this.fileName.equals(file.getName())) {
                    this.writeLog(file);
                }
                break;
            case "-r":
                Pattern pattern = Pattern.compile(fileName);
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.find()) {
                    this.writeLog(file);
                }
                break;
            default:
                throw new UnsupportedOperationException("not known search key");
        }
        return FileVisitResult.CONTINUE;
    }

    protected void writeLog(File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(result, true)) {
            fos.write((file.getAbsolutePath() + "\n").getBytes());
        }
    }
}
