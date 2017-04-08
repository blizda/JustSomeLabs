package firstLab;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SoketWriteInFile{
    public SoketWriteInFile(Document massage) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        if (massage.getDocumentElement().getTagName().equals("message")) {
            File file = new File("history.xml");
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.normalize();
                massage.getDocumentElement().normalize();
                Element resivedMassage = massage.getDocumentElement();
                Element root = doc.getDocumentElement();
                Node imported = doc.importNode(resivedMassage, true);
                root.appendChild(imported);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(file)));
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
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
