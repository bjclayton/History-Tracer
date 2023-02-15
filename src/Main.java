
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Helper.Constant;
import controllers.LinuxHistory;
import controllers.WindowsHistory;

public class Main {
    private static String OSName;
    private static String chromePath;
    private static String firefoxPath;
    private static String mEdgePath;
    private static String operaPath;
    private static String vivaldiPath;
    private static String bravePath;


    public static void main(String[] args) {
        OSName = System.getProperty("os.name"); // get the OS name
        loadSettings();

        // Check the os and display his screen
        if (OSName.contains("Windows")){
        new WindowsHistory();
        } else if (OSName.contains("Linux")) {
        new LinuxHistory();
        }else {
        System.out.println("Other OS");
        }
    }


    static void loadSettings(){
        try {
            // creating a constructor of file class and parsing an XML file
            File file = new File("appSettings.xml");

            // an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("database-url");

            // nodeList is not iterable, so we are using for loop
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    chromePath = eElement.getElementsByTagName("chromeDatabasePath")
                    .item(0).getTextContent();
                    mEdgePath = eElement.getElementsByTagName("mEdgeDatabasePath")
                    .item(0).getTextContent();
                    firefoxPath = eElement.getElementsByTagName("firefoxDatabasePath")
                    .item(0).getTextContent();
                    operaPath = eElement.getElementsByTagName("operaDatabasePath")
                    .item(0).getTextContent();
                    vivaldiPath = eElement.getElementsByTagName("vivaldiDatabasePath")
                    .item(0).getTextContent();
                    bravePath = eElement.getElementsByTagName("braveDatabasePath")
                    .item(0).getTextContent();

                    Constant.setChromeDatabasePath(chromePath);
                    Constant.setMEdgeDatabasePath(mEdgePath);
                    Constant.setFirefoxDatabasePath(firefoxPath);
                    Constant.setOperaDatabasePath(operaPath);
                    Constant.setVivaldiDatabasePath(vivaldiPath);
                    Constant.setBraveDatabasePath(bravePath);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
