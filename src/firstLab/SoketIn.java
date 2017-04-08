package firstLab;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.Socket;

class SoketIn extends Thread {
    private Socket socket;
    private Integer acept = 0;
    private ChatXMLParser cm;
    private Document document;

    public SoketIn(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream sin = null;
        try {
            sin = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                document = new XMLKostyl(sin).receive();
                ChatXMLParser cm = new ChatXMLParser(document);
                if (!LocalHistorKeper.chekAut()) {
                    if (cm.isAutorise()) {
                        LocalHistorKeper.setAut();
                        acept = 1;
                    }
                } else if (acept.equals(1)) {
                    System.out.print(cm.getMassgeHistory().replaceAll((LocalHistorKeper.getLogin() +": +"), ""));
                    acept = acept + 1;
                } else
                    System.out.println(cm.getLogin() + ": " + cm.getMassage());
            } catch (Exception e){

            }
        }
    }
}