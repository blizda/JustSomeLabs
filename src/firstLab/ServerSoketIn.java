package firstLab;

import org.w3c.dom.Document;
import java.io.*;
import java.net.Socket;

class ServerSoketIn extends Thread {
    private final Socket socket;
    private Document doc;
    private SinglString myString;
    private Integer numOfCon;
    private boolean isFerstCon = true;
    private PassCheker pc;
    public ServerSoketIn(Socket socket, Integer numOfCon, SinglString myString, PassCheker pc) {
        this.socket = socket;
        this.myString = myString;
        this.pc = pc;
        this.numOfCon = numOfCon;
    }
    public void run() {
        InputStream sin = null;
        try {
            sin = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            XMLKostyl xmlKostyl = new XMLKostyl(sin);
            doc = xmlKostyl.receive();
            if (xmlKostyl.XMLWasRead() && doc != null) {
                if (isFerstCon){
                    if (pc.isPass(doc)){
                        isFerstCon = false;
                    }
                }
                else if(pc.isAutorise()){
                    myString.setXML(doc, numOfCon);
                    ChatXMLParser ch = new ChatXMLParser(doc);
                    System.out.println(ch.getMassageAndLog());
                }
            }
        }
    }
}

