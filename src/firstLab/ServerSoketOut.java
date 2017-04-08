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

class ServerSoketOut extends Thread {
    private final Socket socket;
    private Document line;
    private SinglString myXML;
    private Integer numOfCon;
    //private SoketWriteInFile file;
    private boolean isFerstCon = true;
    private PassCheker pc;
    public ServerSoketOut(Socket socket, Integer numOfCon, SinglString myXml, PassCheker pc) {
        this.socket = socket;
        this.myXML = myXml;
        this.numOfCon = numOfCon;
        this.pc = pc;
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
                if ((isFerstCon && pc.isAutorise())){
                    isFerstCon = false;
                    pc.ferstAcs = true;
                    new ChatXMLMaker().makeServerAutosie("accepted", out);
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer transformer = tf.newTransformer();
                    transformer.transform(new DOMSource(new SoketReadFile().getXML()),
                            new StreamResult(out));
                    out.flush();
                }
                else if(isFerstCon && !pc.isAutorise() && pc.ferstAcs){
                    new ChatXMLMaker().makeServerAutosie("rejekt", out);
                }
                else if((myXML.getMyXML() != null) && (!(myXML.getMyXML() == line) && !isFerstCon)) {
                    if ((!numOfCon.equals(myXML.getNum()))) {
                        line = myXML.getMyXML();
                        TransformerFactory tf = TransformerFactory.newInstance();
                        Transformer transformer = tf.newTransformer();
                        transformer.transform(new DOMSource(line),
                                new StreamResult(out));
                        out.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        }
    }
}
