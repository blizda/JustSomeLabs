package firstLab;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

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
}
