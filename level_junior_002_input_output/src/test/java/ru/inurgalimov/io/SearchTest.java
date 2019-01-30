package ru.inurgalimov.io;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SearchTest {
    @Test
    public void whenLookingForFiles() {
        File parentDirectory = new File(System.getProperty("java.io.tmpdir") + "/temp");
        parentDirectory.mkdir();
        File childDirectory1 = new File(parentDirectory.getPath() + "/test1");
        childDirectory1.mkdir();
        File childDirectory2 = new File(parentDirectory.getPath() + "/test2");
        childDirectory2.mkdir();
        File testFile1 = new File(parentDirectory.getPath(), "testFile1.txt");
        File testFile2 = new File(parentDirectory.getPath(), "testFile2.xml");
        File testFile3 = new File(childDirectory1.getPath(), "testFile3.xml");
        File testFile4 = new File(childDirectory1.getPath(), "testFile4.txt");
        File testFile5 = new File(childDirectory2.getPath(), "testFile5.txt");
        File testFile6 = new File(childDirectory2.getPath(), "testFile6.xml");
        try (FileOutputStream fos1 = new FileOutputStream(testFile1);
             FileOutputStream fos2 = new FileOutputStream(testFile2);
             FileOutputStream fos3 = new FileOutputStream(testFile3);
             FileOutputStream fos4 = new FileOutputStream(testFile4);
             FileOutputStream fos5 = new FileOutputStream(testFile5);
             FileOutputStream fos6 = new FileOutputStream(testFile6);) {
            fos1.write("test".getBytes());
            fos2.write("test".getBytes());
            fos3.write("test".getBytes());
            fos4.write("test".getBytes());
            fos5.write("test".getBytes());
            fos6.write("test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<String>();
        list.add(".txt");
        List<File> resultList = new Search().files(parentDirectory.getPath(), list);
        assertThat(resultList.get(0).equals(testFile1), is(true));
        assertThat(resultList.get(1).equals(testFile4), is(true));
        assertThat(resultList.get(2).equals(testFile5), is(true));
    }
}