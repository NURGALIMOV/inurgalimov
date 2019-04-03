package ru.inurgalimov.socket;

import java.io.File;

public class UpDirectory implements SimpleActionServer {
    @Override
    public File action(File currentFile, File file, String message) {
        File result = null;
        if (currentFile.equals(file)) {
            result = currentFile;
        } else {
            result = currentFile.getParentFile();
        }
        return result;
    }
}
