package firstLab;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ChatXMLParser {
    private Document document;
    private String massage;
    private String login;
    private String autoriseStatus;
    private String massgeHistory ="";
    public ChatXMLParser(Document doc) {
        this.document = doc;
    }

    private void documentParseMassage() {
        Element root = document.getDocumentElement();
        if(root.getTagName().equals("message")){
            massage = root.getLastChild().getTextContent();
            login = root.getAttribute("autor");
        }
        else if(root.getTagName().equals("massaghistory")){
            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++){
                Node node = children.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    massgeHistory += el.getAttribute("autor") + ": "
                            + el.getLastChild().getNodeValue().replaceAll("\n+", " ").replaceAll(" +"," ") + "\n";
                }
            }
        }
        else
            autoriseStatus = root.getAttribute("status");
    }
    public String getLogin(){
        documentParseMassage();
        return login;
    }
    public String getMassage(){
        documentParseMassage();
        return massage;
    }
    public String getMassgeHistory(){
        documentParseMassage();
        return massgeHistory;
    }
    public boolean isAutorise(){
        documentParseMassage();
        if (autoriseStatus.equals("accepted")){
            return true;
        }
        else
            return false;
    }
}
