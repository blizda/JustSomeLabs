package firstLab;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public class XMLKostyl {
    private InputStream inputStream;
    private XMLEventReader xmlEventReader;
    private DocumentBuilder documentBuilder;
    private boolean isRead = false;
    private StringBuilder sb = new StringBuilder("");
    private Boolean isFerst = true;
    private Integer countStartElements = 0;
    private Integer countEndElements = 0;

    public XMLKostyl(InputStream in) {
        this.inputStream = in;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = dbf.newDocumentBuilder();
            XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
            xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public Document receive() {
        XMLEvent xmlEvent;
        try {
            while((xmlEvent = xmlEventReader.nextEvent())!= null) {
                if (xmlEvent.isStartDocument()){
                    isRead = false;
                    isFerst = false;
                    sb = new StringBuilder();
                }
                sb.append(xmlEvent);
                if (xmlEvent.isStartElement()){
                    countStartElements += 1;
                }
                if (xmlEvent.isEndElement()){
                    countEndElements += 1;
                }
                if (countStartElements.equals(countEndElements) && !isFerst && !countStartElements.equals(0)){
                    countStartElements = 0;
                    countEndElements = 0;
                    isFerst = true;
                    isRead = true;
                    return documentBuilder.parse(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean XMLWasRead(){
        return isRead;
    }
}
