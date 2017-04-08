package firstLab;

import org.w3c.dom.Document;

import java.io.FileNotFoundException;

class SinglString {
    public volatile Document myXML;
    private volatile Integer numOfCon;
    //public Document getString() {
        //return myXML;
    //}
    public void setXML(Document myXML, Integer numOfCon){
        //try {
            new SoketWriteInFile(myXML);
        /*} catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
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
