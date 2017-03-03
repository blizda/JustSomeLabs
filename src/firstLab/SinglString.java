package firstLab;

class SinglString {
    private volatile String myString;
    public String getString() {
        return myString;
    }
    public void setMyString(String myString){
        this.myString = myString;
    }
}
