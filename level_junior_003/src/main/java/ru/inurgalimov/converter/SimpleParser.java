package ru.inurgalimov.converter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimpleParser extends DefaultHandler {
    private String thisElement = "";
    private int sum = 0;
    private int length;

    public SimpleParser(int length) {
        this.length = length;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if ("entry".equals(qName)) {
            this.sum += Integer.parseInt(atts.getValue(0));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }

    public static boolean isNumeric(String str) {
        boolean result = true;
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return result;
    }

    public int getSum() {
        return sum;
    }
}
