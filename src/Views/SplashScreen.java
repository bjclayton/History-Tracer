package Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.net.URL;
import java.net.URLConnection;
import Helper.Constant;
import Models.*;

/**
 * The type Splash screen.
 */
public class SplashScreen extends JFrame {
    private static String File_NAME = "appSettings.json";

    /**
     * Instantiates a new Splash screen.
     *
     * @throws Throwable the throwable
     */
    public SplashScreen() throws Throwable {
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setTitle("History Tracer");

        getContentPane().setBackground(Color.decode("#465f7e"));
        setUndecorated(true);

        if (isConnected()) {
            downloadFileFromUrl();
        }

        loadSettings();

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        add(new MainPane());

        setVisible(true);
    }

    /**
     * Read the json file "appSettings.json".
     *
     * @param key
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private static JSONObject getInfo(String key) throws FileNotFoundException, IOException, ParseException {
        // parsing file "appSettings.json"
        Object obj = new JSONParser().parse(new FileReader(File_NAME));

        // typecasting obj to JSONObject
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject jsonObjKey = (JSONObject) jsonObj.get(key);

        return jsonObjKey;
    }

    /**
     * Get JsonObjects
     *
     * @param jsonObj
     * @return
     */
    private static ArrayList<String> fromJsonAray(Object jsonObj) {
        JSONArray jsonArray = (JSONArray) jsonObj;
        ArrayList<String> array = new ArrayList<>();

        for (Object element : jsonArray.toArray()) {
            array.add((String) element);
        }

        return array;
    }

    /**
     * Check if the use is connected.
     */
    private boolean isConnected() {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Load settings from appSetting.json.
     */
    private static void loadSettings() {
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
                    settings.get("loginDatabasePath").toString(),
                    fromJsonAray(settings.get("sqlCommand")),
                    fromJsonAray(settings.get("siteFields")),
                    fromJsonAray(settings.get("downloadFields")),
                    fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("microsoftEdge");
            Constant.setMicrosoftEdge(new MicrosoftEdge(settings.get("name").toString(),
                    settings.get("iconSrc").toString(),
                    settings.get("databasePath").toString(),
                    settings.get("loginDatabasePath").toString(),
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
                    settings.get("loginDatabasePath").toString(),
                    fromJsonAray(settings.get("sqlCommand")),
                    fromJsonAray(settings.get("siteFields")),
                    fromJsonAray(settings.get("downloadFields")),
                    fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("vivaldi");
            Constant.setVivaldi(new Vivaldi(settings.get("name").toString(),
                    settings.get("iconSrc").toString(),
                    settings.get("databasePath").toString(),
                    settings.get("loginDatabasePath").toString(),
                    fromJsonAray(settings.get("sqlCommand")),
                    fromJsonAray(settings.get("siteFields")),
                    fromJsonAray(settings.get("downloadFields")),
                    fromJsonAray(settings.get("loginFields"))));

            settings = getInfo("brave");
            Constant.setBrave(new Brave(settings.get("name").toString(),
                    settings.get("iconSrc").toString(),
                    settings.get("databasePath").toString(),
                    settings.get("loginDatabasePath").toString(),
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
                    settings.get("loadGif").toString(),
                    settings.get("iconMore").toString()
                    ));

            settings = getInfo("table");
            Constant.setTable(new Table(fromJsonAray(settings.get("tableSite")),
                    fromJsonAray(settings.get("tableDownload")),
                    fromJsonAray(settings.get("tableLogin"))));

            settings = getInfo("dashboard");
            Constant.setDashboard(new Dashboard(settings.get("name").toString(),
                    settings.get("iconSrc").toString(),
                    settings.get("databasePath").toString(),
                    fromJsonAray(settings.get("sqlCommand")),
                    fromJsonAray(settings.get("databasedFields"))));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Download file ("appSettings.json") from url.
     *
     * @throws IOException the io exception
     */
    public static void downloadFileFromUrl() throws IOException {
        String OSName = System.getProperty("os.name"); // get the OS name
        String fileID = "";
        // Check the os and display the screen
        if (OSName.contains("Windows")) {
            fileID = "1wLcKz6gBNyNMl9_D4WUQsW50ufm3bCYs";
        } else if (OSName.contains("Linux")) {
            fileID = "1GhiEwmkrG4IJJBjcxzERLdVtsO5l9bxy";
        }
        
        String downloadURL = "https://drive.google.com/uc?id=" + fileID;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(downloadURL).openStream()))) {
            String line;
            FileWriter fileWriter = new FileWriter("appSettings.json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while ((line = reader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The type Main pane.
     */
    public class MainPane extends JPanel {

        /**
         * Instantiates a new Main pane.
         */
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
