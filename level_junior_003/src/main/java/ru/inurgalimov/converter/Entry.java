package ru.inurgalimov.converter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entry {
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    @XmlElement
    public void setField(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }
}
