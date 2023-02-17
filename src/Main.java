import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
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
import controllers.LinuxHistory;
import controllers.WindowsHistory;

public class Main {
    private static String OSName;


    public static void main(String[] args) {
        OSName = System.getProperty("os.name"); // get the OS name
        loadSettings();

        // Check the os and display the screen
        if (OSName.contains(Constant.getWindows().getName())){
            new WindowsHistory();
        } else if (OSName.contains(Constant.getLinux().getName())) {
            new LinuxHistory();
        }else {
            System.out.println("Other OS");
        }
    }


    static void loadSettings(){
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
                                            settings.get("iconSelectAll").toString()));

            settings = getInfo("table");
            Constant.setTable(new Table(fromJsonAray(settings.get("tableSite")),
                                        fromJsonAray(settings.get("tableDownload")),
                                        fromJsonAray(settings.get("tableLogin"))));

        } catch (Exception e) {
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
}
