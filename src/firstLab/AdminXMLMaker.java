package firstLab;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
}
