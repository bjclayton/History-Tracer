package Helper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;

import Models.Browser;
import Models.Downloads;
import Models.Login;
import Models.SiteHistory;
import java.sql.Timestamp;
import java.util.Date;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDatabase {
    // ------------------------------------ All variables
    private static ArrayList<SiteHistory> listInfo = new ArrayList<>();
    private static ArrayList<Downloads> downloadsData = new ArrayList<>();
    private static ArrayList<Login> loginData = new ArrayList<>();
    private static Connection Conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static String OSName = System.getProperty("os.name"); // get the OS name
    private static String username = System.getProperty("user.name");
    private static String urlDatabase, query, databaseName;

    // -------------------- Method to open connection
    public static void setConn() {
        try {
            Conn = DriverManager.getConnection(Constant.getWindows().getDatabaseName());
            stmt = Conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // -------------------- Method to close the connection
    public static void close() throws SQLException {
        Conn.close();
    }

    // ------------------------------------------- Search chrome history
    public static ArrayList<SiteHistory> ChromeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if (!Objects.equals(choice, "Display")) {
            query = String.format(Constant.getChrome().getSqlCommand().get(1), choice);
        } else {
            query = Constant.getChrome().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getChrome().getDatabasePath();
        copyDatabase(String.format(Constant.getChrome().getDatabasePath(), username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String url = rs.getString(Constant.getChrome().getSiteFields().get(0));
                String title = rs.getString(Constant.getChrome().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getChrome().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getChrome().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if (Constant.getDates().containsKey(convertTime(visitTime))) {
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                } else {
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }

    // ------------------------------------------- Search Microsoft Edge history
    public static ArrayList<SiteHistory> microsoftEdgeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if (!Objects.equals(choice, "Display")) {
            query = String.format(Constant.getMicrosoftEdge().getSqlCommand().get(1), choice);
        } else {
            query = Constant.getMicrosoftEdge().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getMicrosoftEdge().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String url = rs.getString(Constant.getMicrosoftEdge().getSiteFields().get(0));
                String title = rs.getString(Constant.getMicrosoftEdge().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getMicrosoftEdge().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getMicrosoftEdge().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if (Constant.getDates().containsKey(convertTime(visitTime))) {
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                } else {
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }

    // ------------------------------------------- Search Firefox history
    public static ArrayList<SiteHistory> firefoxHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;


        if (!Objects.equals(choice, "Display")) {
            query = String.format(Constant.getFirefox().getSqlCommand().get(1), choice);
        } else {
            query = Constant.getFirefox().getSqlCommand().get(0);
        }

        urlDatabase = String.format(Constant.getFirefox().getDatabasePath(), username, getProfile());

        copyDatabase(urlDatabase);
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String url = rs.getString(Constant.getFirefox().getSiteFields().get(0));
                String title = rs.getString(Constant.getFirefox().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getFirefox().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getFirefox().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if (Constant.getDates().containsKey(convertTime(visitTime))) {
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                } else {
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }


    private static String getProfile(){
        String profilePath = System.getProperty("user.home") + "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\";
        File profileDir = new File(profilePath);
        File[] profiles = profileDir.listFiles();

        for (File profile : profiles) {
            if (profile.isDirectory() &&  profile.getName().contains(".default")) {
                String profileName = profile.getName();
                Path databasePath = Paths.get(profile.getAbsolutePath(), "places.sqlite");

                // Check if the database file exists
                if (Files.exists(databasePath)) {
                    return profileName;
                }
            }
        }
        return "";
    }


    // ------------------------------------------- Search Opera history
    public static ArrayList<SiteHistory> operaHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if (!Objects.equals(choice, "Display")) {
            query = String.format(Constant.getOpera().getSqlCommand().get(1), choice);
        } else {
            query = Constant.getOpera().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getOpera().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String url = rs.getString(Constant.getOpera().getSiteFields().get(0));
                String title = rs.getString(Constant.getOpera().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getOpera().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getOpera().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if (Constant.getDates().containsKey(convertTime(visitTime))) {
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                } else {
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }

    // ------------------------------------------- Search Vivaldi history
    public static ArrayList<SiteHistory> vivaldiHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if (!Objects.equals(choice, "Display")) {
            query = String.format(Constant.getVivaldi().getSqlCommand().get(1), choice);
        } else {
            query = Constant.getVivaldi().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getVivaldi().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String url = rs.getString(Constant.getVivaldi().getSiteFields().get(0));
                String title = rs.getString(Constant.getVivaldi().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getVivaldi().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getVivaldi().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if (Constant.getDates().containsKey(convertTime(visitTime))) {
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                } else {
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }

    // ------------------------------------------- Search Brave history
    public static ArrayList<SiteHistory> braveHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if (!Objects.equals(choice, "Display")) {
            query = String.format(Constant.getBrave().getSqlCommand().get(1), choice);
        } else {
            query = Constant.getBrave().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getBrave().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String url = rs.getString(Constant.getBrave().getSiteFields().get(0));
                String title = rs.getString(Constant.getBrave().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getBrave().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getBrave().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if (Constant.getDates().containsKey(convertTime(visitTime))) {
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                } else {
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }

    public static String convertTime(String date) {
        // Getting the current system time and passing it and passing the long value in
        // the Date class
        if (date != null) {
            Timestamp ts = new Timestamp(Long.parseLong(date));
            Date newDate = new Date(ts.getTime());
            String val = String.valueOf(newDate);

            String[] listDate = val.split(" ");
            String valDateString = String.format("%s %s %s", listDate[0], listDate[2], listDate[2]);

            return valDateString;
        } else {
            return "None";
        }
    }

    // Copy the database (To avoid an error like "database is locked")
    public static void copyDatabase(String path) throws IOException, SQLException {
        File source = new File(path);
        File destination = null;

        if (OSName.contains(Constant.getWindows().getName())) {
            destination = new File("windowsDatabase.sqlite");
            Files.deleteIfExists(Path.of("windowsDatabase.sqlite"));
            Files.copy(source.toPath(), destination.toPath());

        } else if (Objects.equals(OSName, Constant.getLinux().getName())) {
            Files.deleteIfExists(Path.of("linuxDatabase.sqlite"));
            destination = new File("linuxDatabase.sqlite");
            Files.copy(source.toPath(), destination.toPath());
        } else {
            System.out.println("Other OS");
        }
    }


    // ------------------------------------------- Search chrome history
    public static ArrayList<Downloads> browserDownload(String name) throws IOException, SQLException {
        downloadsData.clear();
        Browser browser = null;

        switch (name) {
            case "Chrome":
                browser = Constant.getChrome();        
                break;
            case "Microsoft Edge":
                browser = Constant.getMicrosoftEdge();        
                break;
            case "Opera":
                browser = Constant.getOpera();        
                break;
            case "Vivaldi":
                browser = Constant.getVivaldi();        
                break;
            case "Brave":
                browser = Constant.getBrave();        
                break;
            default:
                break;
        } 
    
        if(!name.equals("Firefox")){

            Constant.setBrowser(browser);

            try {
                setConn();
    
                query = Constant.getBrowser().getSqlCommand().get(2);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String referrer = rs.getString(Constant.getBrowser().getDownloadFields().get(0));
                    String current_path = rs.getString(Constant.getBrowser().getDownloadFields().get(1));
                    String total_bytes = rs.getString(Constant.getBrowser().getDownloadFields().get(2));
    
                    Downloads info = new Downloads(referrer, current_path, total_bytes);
                    downloadsData.add(info);
                }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            close();    
        }
        
        return downloadsData;
    }


    // ------------------------------------------- Search chrome history
    public static ArrayList<Login> browserLogins(String name) throws IOException, SQLException {
        loginData.clear();
        Browser browser = null;

        switch (name) {
            case "Chrome":
                browser = Constant.getChrome();        
                break;
            case "Microsoft Edge":
                browser = Constant.getMicrosoftEdge();        
                break;
            case "Opera":
                browser = Constant.getOpera();        
                break;
            case "Vivaldi":
                browser = Constant.getVivaldi();        
                break;
            case "Brave":
                browser = Constant.getBrave();        
                break;
            default:
                break;
        } 
        
        if(!name.equals("Firefox")){

            Constant.setBrowser(browser);
            copyLoginDatabase(String.format(Constant.getBrowser().getLoginDatabasePath(), username));

            try {
                Conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
                stmt = Conn.createStatement();

                query = Constant.getBrowser().getSqlCommand().get(3);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String url = rs.getString(Constant.getBrowser().getLoginFields().get(0));
                    String username_value = rs.getString(Constant.getBrowser().getLoginFields().get(1));
    
                    Login info = new Login(url, username_value);
                    loginData.add(info);
                }
    
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            Conn.close();    
        }
        
        return loginData;
    }

    // Copy the database (To avoid an error like "database is locked")
    public static void copyLoginDatabase(String path) throws IOException, SQLException {
        File source = new File(path);
        File destination = null;

        if (OSName.contains(Constant.getWindows().getName())) {
            destination = new File("windowsLoginDatabase.sqlite");
            Files.deleteIfExists(Path.of("windowsLoginDatabase.sqlite"));
            Files.copy(source.toPath(), destination.toPath());
            databaseName = "windowsLoginDatabase.sqlite";
        } else if (Objects.equals(OSName, Constant.getLinux().getName())) {
            Files.deleteIfExists(Path.of("linuxLoginDatabase.sqlite"));
            destination = new File("linuxLoginDatabase.sqlite");
            Files.copy(source.toPath(), destination.toPath());
            databaseName = "linuxLoginDatabase.sqlite";
        } else {
            databaseName = "";
            System.out.println("Other OS");
        }
    }


    public static void exportData(ArrayList<SiteHistory> data){
        if(data != null){
            String csvFile = "";
            // Set the path and filename of the CSV file to create
            if(System.getProperty("os.name").contains(Constant.getWindows().getName())){
                csvFile = "C:\\Users\\" + username + "\\Desktop\\" + "data.csv";
            } else if (OSName.contains(Constant.getLinux().getName())) {
                String Username = System.getenv("USER");
                csvFile = "/home/" + Username + "/Desktop/" +"data.csv";
            }
            
            // Define the data to be written to the CSV file
            String[] headers = {"Url", "Title", "Visit Time", "Visit Count", "User Profile"};
    
            // Create a BufferedWriter object to write to the CSV file
            BufferedWriter bw = null;
    
            try {
                // Initialize the BufferedWriter object with a FileWriter
                bw = new BufferedWriter(new FileWriter(csvFile, StandardCharsets.UTF_8));
                
                // Write the headers to the CSV file
                for (String header : headers) {
                    bw.write(header + ",");
                }
                bw.newLine();
                
                // Write the data to the CSV file
                for(SiteHistory siteHistory : data){
                    String[] line = {siteHistory.getUrl(), siteHistory.getTitle(), siteHistory.getVisitTime(), String.valueOf(siteHistory.getVisitCount()), siteHistory.getUserProfile()};
                    bw.write(String.join(",", line));
                    bw.newLine();
                }
                
                // Close the BufferedWriter object
                bw.close();
                JOptionPane.showMessageDialog(null, "The CSV file exports successfully.", "History Tracer", 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
