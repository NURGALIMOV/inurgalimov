package ru.inurgalimov.io;

import java.io.File;
import java.util.*;

/**
 * @author Nurgalimov Ilshat
 * @version 19.01.2019
 */
public class Search {
    /**
     * @param parent - это путь до каталога с которого нужно осуществлять поиск.
     * @param exts   - это расширения файлов, которые мы хотим получить.
     * @return - список всех файлов с конкретным расширение.
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> resultList = new ArrayList<>();
        Queue<File> fileQueue = new LinkedList<>();
        File file = new File(parent);
        Arrays.stream(file.listFiles()).forEach(a -> fileQueue.add(a));
        File tempFile;
        while (!fileQueue.isEmpty()) {
            tempFile = fileQueue.poll();
            if (tempFile.isDirectory()) {
                Arrays.stream(tempFile.listFiles()).forEach(a -> fileQueue.add(a));
            } else {
                for (String s : exts) {
                    if (tempFile.getName().endsWith(s)) {
                        resultList.add(tempFile);
                        break;
                    }
                }
            }
        }
        return resultList;
    }
}
