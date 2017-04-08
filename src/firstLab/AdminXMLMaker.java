package firstLab;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Base64;

public class AdminXMLMaker {
    public static Document makeXML(String login, String massge) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document newDoc = db.newDocument();
            Element root = newDoc.createElement("message");
            newDoc.appendChild(root);
            Attr autor = newDoc.createAttribute("autor");
            autor.setValue(login);
            root.setAttributeNode(autor);
            Text massgeText = newDoc.createTextNode(massge);
            root.appendChild(massgeText);
            return newDoc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document maceSand(String login, String fileName) {
        File file = new File(fileName);
        try {
            FileInputStream fileInput = new FileInputStream(file);
            byte[] fileData = new byte[(int) file.length()];
            fileInput.read(fileData);
            fileInput.close();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document newDoc = builder.newDocument();
            Element root = newDoc.createElement("file");
            newDoc.appendChild(root);
            Attr autor = newDoc.createAttribute("autor");
            autor.setValue(login);
            root.setAttributeNode(autor);
            Attr filename = newDoc.createAttribute("fileName");
            filename.setValue(fileName);
            root.setAttributeNode(filename);
            CDATASection cdata = newDoc.createCDATASection(Base64.getEncoder().encodeToString(fileData));
            root.appendChild(cdata);
            return newDoc;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
