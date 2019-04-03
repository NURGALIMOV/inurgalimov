package ru.inurgalimov.socket;

import java.io.File;

public interface SimpleActionServer {
    File action(File currentFile, File file, String message);
}
