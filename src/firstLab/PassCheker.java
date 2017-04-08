package firstLab;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PassCheker {
    private File file = new File("pass.txt");
    private String str = "";
    private Integer id;
    private boolean isAut = false;
    public boolean ferstAcs = false;
    public PassCheker(Integer id) {
        this.id = id;
    }
    public boolean isPass(Document doc) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                str = in.nextLine().trim();
                /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document parseDoc = db.parse((File) doc);*/
                Element root = doc.getDocumentElement();
                String log = root.getAttribute("login");
                String pass = root.getAttribute("password");
                //System.out.println("qq from pc");
                String logAndPass = log + " " + pass;
                if (logAndPass.equals(str)){
                    NameHolder.addName(id, str.split(" ")[0]);
                    isAut = true;
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*public boolean isPass(String logAndPass) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                str = in.nextLine().trim();
                if (logAndPass.equals(str)){
                    NameHolder.addName(id, str.split(" ")[0]);
                    isAut = true;
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }*/
    public boolean isAutorise(){
        return isAut;
    }
}
