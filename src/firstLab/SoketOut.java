package firstLab;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.Socket;

class SoketOut extends Thread {
    private String line = null;
    private Socket socket;
    private BufferedReader keyboard;
    private boolean isAutorise = false;
    public SoketOut (Socket socket, BufferedReader keyboard) {
        this.socket = socket;
        this.keyboard = keyboard;
    }
    public void run() {
        OutputStream sout = null;
        try {
            sout = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedOutputStream out = new BufferedOutputStream(sout);
        while (true) {
            try {
                line = keyboard.readLine();
                if (!LocalHistorKeper.chekAut()) {
                    String[] logAndPass = line.split(" ");
                    new ChatXMLMaker().makeAutorise(logAndPass[0], logAndPass[1], out);
                    LocalHistorKeper.setLogin(logAndPass[0]);
                }
                else {
                    if (line.startsWith("/sendfile")){
                        String[] fileName = line.split(" ");
                        new ChatXMLMaker().makeXMLFileWithFileInside(LocalHistorKeper.getLogin(), fileName[1], out);
                    }
                    else {
                        new ChatXMLMaker().makeXML(LocalHistorKeper.getLogin(), line, out);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}