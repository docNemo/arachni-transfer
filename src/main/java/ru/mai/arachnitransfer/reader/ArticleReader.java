package ru.mai.arachnitransfer.reader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

//@Slf4j
public class ArticleReader {
    public ArticleXml readArticleFromXml(final String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(ArticleXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ArticleXml) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
//            log.error(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
