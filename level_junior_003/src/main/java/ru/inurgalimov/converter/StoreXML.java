package ru.inurgalimov.converter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class StoreXML {
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(StoreSQL.Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new StoreSQL.Entries(list), new FileWriter(this.target, true));
    }
}
