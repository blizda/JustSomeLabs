package firstLab;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Base64;

public class ChatXMLMaker {
    private BufferedOutputStream os;
    public ChatXMLMaker(){}
    public void makeXML(String login, String massge, BufferedOutputStream outputStream){
        this.os = outputStream;
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
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(newDoc),
                    new StreamResult(os));
            os.flush();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void makeAutorise(String login, String pass, BufferedOutputStream outputStream){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            this.os = outputStream;
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document newDoc = db.newDocument();
            Element root = newDoc.createElement("autorisation");
            newDoc.appendChild(root);
            Attr autor = newDoc.createAttribute("login");
            autor.setValue(login);
            root.setAttributeNode(autor);
            Attr pas = newDoc.createAttribute("password");
            pas.setValue(pass);
            root.setAttributeNode(pas);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(newDoc),
                    new StreamResult(os));
            os.flush();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void makeServerAutosie(String status, BufferedOutputStream outputStream){
        this.os = outputStream;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document newDoc = db.newDocument();
            Element root = newDoc.createElement("autorisation");
            newDoc.appendChild(root);
            Attr autor = newDoc.createAttribute("status");
            autor.setValue(status);
            root.setAttributeNode(autor);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(newDoc),
                    new StreamResult(os));
            os.flush();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void makeXMLFileWithFileInside(String login, String fileName, BufferedOutputStream outputStream){
        File file = new File(fileName);
        this.os = outputStream;
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
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(newDoc),
                    new StreamResult(os));
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
