package firstLab;

import java.io.BufferedReader;
import java.io.IOException;

class ReadFromKeyboard extends Thread {
    private SinglString myString;
    private BufferedReader keyboard;
    public ReadFromKeyboard(BufferedReader keyboard, SinglString myString) {
        this.keyboard = keyboard;
        this.myString = myString;
    }
    public void run() {
        while (true) {
            try {
                String line = keyboard.readLine();
                myString.setXML(AdminXMLMaker.makeXML("admin", line), -1);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
