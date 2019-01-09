package ru.inurgalimov.io;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortingFileTest {
    @Test
    public void whenWeSortTheFile() {
        File f1 = new File("src/main/resources/", "source.txt");
        File f2 = new File("src/main/resources/", "distance.txt");
        new SortingFile().sort(f1, f2);
        long l = 19398;
        String s1 = "The ";
        String s2 = "SBE, via Agrona, provides an abstraction to Java, with the MutableDirectBuffer class, to ";
        String s3 = "I often see people complain that a codec cannot support a particular presentation in a "
                + "single message. However this is often possible to address with a protocol of messages. "
                + "Protocols are a great way to split an interaction into its component parts, these parts "
                + "are then often composable for many interactions between systems. For example, the IR "
                + "implementation of schema metadata is more complex than can be supported by the structure of "
                + "a single message. We encode IR by first sending a template message providing an overview, "
                + "followed by a stream of messages, each encoding the tokens from the compiler IR. This allows "
                + "for the design of a very fast OTF decoder which can be implemented as a threaded interpreter "
                + "with much less branching than the typical switch based state machines.";
        List<String> list = new ArrayList<>();
        try (RandomAccessFile r = new RandomAccessFile(f2, "rw")) {
            String temp;
            while ((temp = r.readLine()) != null) {
                list.add(temp);
            }
            assertThat((l == list.size()), is(true));
            assertThat(list.get(0).equals(s1), is(true));
            assertThat(list.get(list.size() / 2).equals(s2), is(true));
            assertThat(list.get(list.size() - 1).equals(s3), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        f2.delete();
    }
}