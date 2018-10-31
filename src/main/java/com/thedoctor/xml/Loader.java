package com.thedoctor.xml;

import org.w3c.dom.Element;

import java.io.File;

public abstract class Loader {

    File file;
    Element doc;

    public Loader(String path){
        this.file = new File(path);
    }

    public Loader(File file){
        this.file = file;
    }

    public void load(){
        this.doc = (Element) Reader.getElement(this.file);
    }
}
