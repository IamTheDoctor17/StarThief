package com.thedoctor.xml;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class Reader {

    public static Element getElement(String path) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(path);
            return doc.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return (Element) doc;
    }

    public static Element getElement(File file){
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(file);
            return doc.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return (Element) doc;
    }
}
