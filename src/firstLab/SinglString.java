package firstLab;

import org.w3c.dom.Document;

import java.io.FileNotFoundException;

class SinglString {
    public volatile Document myXML;
    private volatile Integer numOfCon;
    public void setXML(Document myXML, Integer numOfCon){
        new SoketWriteInFile(myXML);
        this.numOfCon = numOfCon;
        this.myXML = myXML;
    }
    public Integer getNum(){
        return numOfCon;
    }
    public Document getMyXML(){
        return myXML;
    }
}
