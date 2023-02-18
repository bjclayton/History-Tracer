package Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.net.URL;
import java.net.URLConnection;
import Helper.Constant;
import Models.*;

public class SplashScreen extends JFrame{
    private String FILE_URL = "https://drive.google.com/file/d/18KZX2UqT6olXlw4dtqiWpwWw1up-8oCX/view?usp=share_link";
    private String  File_NAME = "appSettings.json";

    public SplashScreen() throws Throwable{
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setTitle("History Tracer");

        getContentPane().setBackground(Color.decode("#465f7e"));
        setUndecorated(true);
         
        if(isConnected()){
            //downloadFile();
        }

        loadSettings();

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        add(new MainPane());

        setVisible(true);
    }


    private static JSONObject getInfo(String key) throws FileNotFoundException, IOException, ParseException{
        // parsing file "appSettings.json"
        Object obj = new JSONParser().parse(new FileReader("appSettings.json"));
                    
        // typecasting obj to JSONObject
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject jsonObjKey = (JSONObject) jsonObj.get(key);

        return jsonObjKey;
    }

    
    private static ArrayList<String> fromJsonAray(Object jsonObj){
        JSONArray jsonArray = (JSONArray) jsonObj;
        ArrayList<String> array = new ArrayList<>();

        for (Object element : jsonArray.toArray()) {
            array.add((String) element);
        }

        return array;
    }


    private boolean isConnected(){
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }


    private static void loadSettings(){
        JSONObject settings;
        try { 
            settings = getInfo("windows");
            Constant.setWindows(new Windows(settings.get("name").toString(), 
                                            settings.get("title").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databaseName").toString()));
            
            settings = getInfo("linux");
            Constant.setLinux(new Linux(settings.get("name").toString(), 
                                            settings.get("title").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databaseName").toString()));

            settings = getInfo("chrome");
            Constant.setChrome(new Chrome(settings.get("name").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databasePath").toString(), 
                                            fromJsonAray(settings.get("sqlCommand")),
                                            fromJsonAray(settings.get("siteFields")),
                                            fromJsonAray(settings.get("downloadFields")),
                                            fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("microsoftEdge");
            Constant.setMicrosoftEdge(new MicrosoftEdge(settings.get("name").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databasePath").toString(), 
                                            fromJsonAray(settings.get("sqlCommand")),
                                            fromJsonAray(settings.get("siteFields")),
                                            fromJsonAray(settings.get("downloadFields")),
                                            fromJsonAray(settings.get("loginFields"))));


            settings = getInfo("firefox");
            Constant.setFirefox(new Firefox(settings.get("name").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databasePath").toString(), 
                                            fromJsonAray(settings.get("sqlCommand")),
                                            fromJsonAray(settings.get("siteFields")),
                                            fromJsonAray(settings.get("downloadFields")),
                                            fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("opera");
            Constant.setOpera(new Opera(settings.get("name").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databasePath").toString(), 
                                            fromJsonAray(settings.get("sqlCommand")),
                                            fromJsonAray(settings.get("siteFields")),
                                            fromJsonAray(settings.get("downloadFields")),
                                            fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("vivaldi");
            Constant.setVivaldi(new Vivaldi(settings.get("name").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databasePath").toString(), 
                                            fromJsonAray(settings.get("sqlCommand")),
                                            fromJsonAray(settings.get("siteFields")),
                                            fromJsonAray(settings.get("downloadFields")),
                                            fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("brave");
            Constant.setBrave(new Brave(settings.get("name").toString(), 
                                            settings.get("iconSrc").toString(), 
                                            settings.get("databasePath").toString(), 
                                            fromJsonAray(settings.get("sqlCommand")),
                                            fromJsonAray(settings.get("siteFields")),
                                            fromJsonAray(settings.get("downloadFields")),
                                            fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("icons");
            Constant.setIcons(new Icons(settings.get("iconApp").toString(), 
                                            settings.get("iconCopy").toString(), 
                                            settings.get("iconSort").toString(), 
                                            settings.get("iconRefresh").toString(),
                                            settings.get("iconSelectAll").toString(),
                                            settings.get("loadGif").toString()));

            settings = getInfo("table");
            Constant.setTable(new Table(fromJsonAray(settings.get("tableSite")),
                                        fromJsonAray(settings.get("tableDownload")),
                                        fromJsonAray(settings.get("tableLogin"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class MainPane extends JPanel {

        public MainPane() {
            setBorder(new EmptyBorder(100, 10, 10, 10));
            setLayout(new GridBagLayout());
            setBackground(Color.decode("#465f7e"));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            JLabel lblTitle = new JLabel("<html><h1><strong><i>History Tracer</i></strong></h1></html>");
            lblTitle.setForeground(Color.BLACK);
            add(lblTitle, gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel labelPanel = new JPanel(new GridBagLayout());
            Icon imgIcon = new ImageIcon(Constant.getIcons().getLoadGif());
            labelPanel.add(new JLabel(imgIcon), gbc);

            gbc.weighty = 1;
            add(labelPanel, gbc);
        }
    }
}
