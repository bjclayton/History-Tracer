import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import Helper.Constant;
import Models.Chrome;
import Models.Linux;
import Models.Windows;
import controllers.LinuxHistory;
import controllers.WindowsHistory;

public class Main {
    private static String OSName;


    public static void main(String[] args) {
        OSName = System.getProperty("os.name"); // get the OS name
        loadSettings();

        // Check the os and display his screen
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
