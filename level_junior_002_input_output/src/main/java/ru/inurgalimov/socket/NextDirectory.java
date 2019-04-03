package ru.inurgalimov.socket;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class NextDirectory implements SimpleActionServer {
    @Override
    public File action(File currentFile, File file, String message) {
        List<File> list = Arrays.asList(currentFile.listFiles());
        File result = currentFile;
        for (File f : list) {
            if (f.getName().equals(message)) {
                result = f;
                break;
            }
        }
        return result;
    }
}
