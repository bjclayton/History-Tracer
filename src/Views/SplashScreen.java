package Views;

import java.awt.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import Helper.Constant;
import Models.Brave;
import Models.Chrome;
import Models.Firefox;
import Models.Icons;
import Models.Linux;
import Models.MicrosoftEdge;
import Models.Opera;
import Models.Table;
import Models.Vivaldi;
import Models.Windows;

public class SplashScreen extends JFrame{

    public SplashScreen(){
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("History Tracer");

         
        if(isConnected()){
            downloadFile();
        }

        loadSettings();

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        Icon imgIcon = new ImageIcon(Constant.getIcons().getLoadGif());
        JLabel label = new JLabel(imgIcon);
        label.setBounds(668, 43, 46, 14);
        getContentPane().add(label);

        setVisible(true);
    }

    private void downloadFile(){
        String urlStr = "https://drive.google.com/file/d/18KZX2UqT6olXlw4dtqiWpwWw1up-8oCX/view?usp=share_link";
        
        try {            
            URL url = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream("appSettings.json");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
