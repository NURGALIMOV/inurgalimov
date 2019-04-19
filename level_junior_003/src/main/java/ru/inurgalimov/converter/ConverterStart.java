package ru.inurgalimov.converter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class ConverterStart {
    private Config config;
    private File scheme;
    private File result;
    private File source;
    private String path;
    private int size;

    public ConverterStart(File source, int size) {
        String temp = this.getClass().getResource("\\").getFile();
        this.path = temp.substring(0, temp.indexOf("target")) + "src/main/resources/";
        this.scheme = new File(path + "scheme.xml");
        this.result = new File(path + "result.xml");
        this.source = source;
        this.config = new Config();
        this.size = size;
    }

    public static void main(String[] args) {
        ConverterStart converterStart = new ConverterStart(new File(args[0]), Integer.parseInt(args[1]));
        System.out.println(converterStart.start());
    }

    public int start() {
        int sum = 0;
        this.config.init();
        StoreSQL storeSQL = new StoreSQL(this.config);
        storeSQL.initDB("converter.db");
        storeSQL.generate(this.size);
        StoreXML storeXML = new StoreXML(this.source);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        try {
            storeXML.save(storeSQL.load());
            convertXSQT.convert(this.source, this.result, this.scheme);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SimpleParser saxp = new SimpleParser(this.size);
            parser.parse(this.result, saxp);
            sum = saxp.getSum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }
}
