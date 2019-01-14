package ru.inurgalimov.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archiver {
    public static void main(String[] args) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File("C:/java/", "archive.zip")));
        File file = new File("C:/java/test");
        doZip(file, out);
        out.close();
    }

    private static void doZip(File dir, ZipOutputStream out) throws IOException {
        for (File f : dir.listFiles()) {
            if (f.isDirectory())
                doZip(f, out);
            else {
                out.putNextEntry(new ZipEntry(f.getPath()));
                write(new FileInputStream(f), out);
                out.closeEntry();
            }
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        in.close();
    }
}