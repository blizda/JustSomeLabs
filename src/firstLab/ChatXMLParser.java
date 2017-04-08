package firstLab;

import org.w3c.dom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

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
        else if(root.getTagName().equals("file")){
            String data = document.getDocumentElement().getFirstChild().getTextContent();
            login = "system";
            String fileName = root.getAttribute("fileName");
            byte[] fileData = Base64.getDecoder().decode(data);
            String newFileName = ResiveFileMaker.makeFile(fileName);
            try(FileOutputStream out = new FileOutputStream(newFileName)){
                out.write(fileData);
                out.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            massage = "файл " + newFileName + " от пользователя " + document.getDocumentElement().getAttribute("autor")
                    + " успешно получен";

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
    public String getMassageAndLog(){
        documentParseMassage();
        return login + ": " + massage;
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
