package firstLab;

import java.io.FileNotFoundException;

class SinglString {
    private volatile String myString;
    private volatile Integer numOfCon;
    public String getString() {
        return myString;
    }
    public void setMyString(String myString, Integer numOfCon){
        try {
            new SoketWriteInFile(NameHolder.getNameFromId(numOfCon) + ": " + myString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.numOfCon = numOfCon;
        this.myString = myString;
    }
    public Integer getNum(){
        return numOfCon;
    }
    /*public void setNumOfCon(int numOfCon){
        this.numOfCon = numOfCon;
    }*/
}
