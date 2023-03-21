package Helper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import Models.SiteHistory;
import java.sql.Timestamp;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;  

public class DashboardDatabase {
    // ------------------------------------ All variables -----------------------------------
    private static ArrayList<SiteHistory> listInfo = new ArrayList<>();
    private static Connection Conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static String OSName = System.getProperty("os.name"); // get the OS name
    private static String username = System.getProperty("user.name");
    private static String urlDatabase, query;


    // -------------------- Method to open connection
    public static void setConn(){
            try {
                Conn = DriverManager.getConnection(Constant.getWindows().getDatabaseName());
                stmt = Conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    // -------------------- Method to close the connection
    public static void close() throws SQLException {Conn.close();}

    // ------------------------------------------- Search chrome history ---------------------------------------
    public static ArrayList<SiteHistory> ChromeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if(!Objects.equals(choice, "Display")){
            query = String.format(Constant.getChrome().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getChrome().getSqlCommand().get(0);
        }


        urlDatabase = Constant.getChrome().getDatabasePath();
        copyDatabase(String.format(Constant.getChrome().getDatabasePath(), username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString(Constant.getChrome().getSiteFields().get(0));
                String title = rs.getString(Constant.getChrome().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getChrome().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getChrome().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if(Constant.getDates().containsKey(convertTime(visitTime))){
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                }else{
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }


    // ------------------------------------------- Search Microsoft Edge history ---------------------------------------
    public static ArrayList<SiteHistory>  microsoftEdgeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if(!Objects.equals(choice, "Display")){
            query = String.format(Constant.getMicrosoftEdge().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getMicrosoftEdge().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getMicrosoftEdge().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString(Constant.getMicrosoftEdge().getSiteFields().get(0));
                String title = rs.getString(Constant.getMicrosoftEdge().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getMicrosoftEdge().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getMicrosoftEdge().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if(Constant.getDates().containsKey(convertTime(visitTime))){
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                }else{
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }


    // ------------------------------------------- Search Firefox history ---------------------------------------
    public static ArrayList<SiteHistory>  firefoxHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if(!Objects.equals(choice, "Display")){
            query = String.format(Constant.getFirefox().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getFirefox().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getFirefox().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString(Constant.getFirefox().getSiteFields().get(0));
                String title = rs.getString(Constant.getFirefox().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getFirefox().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getFirefox().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if(Constant.getDates().containsKey(convertTime(visitTime))){
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                }else{
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }


    // ------------------------------------------- Search Opera history ---------------------------------------
    public static ArrayList<SiteHistory>  operaHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if(!Objects.equals(choice, "Display")){
            query = String.format(Constant.getOpera().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getOpera().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getOpera().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString(Constant.getOpera().getSiteFields().get(0));
                String title = rs.getString(Constant.getOpera().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getOpera().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getOpera().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if(Constant.getDates().containsKey(convertTime(visitTime))){
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                }else{
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }


    // ------------------------------------------- Search Vivaldi history ---------------------------------------
    public static ArrayList<SiteHistory>  vivaldiHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if(!Objects.equals(choice, "Display")){
            query = String.format(Constant.getVivaldi().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getVivaldi().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getVivaldi().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString(Constant.getVivaldi().getSiteFields().get(0));
                String title = rs.getString(Constant.getVivaldi().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getVivaldi().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getVivaldi().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if(Constant.getDates().containsKey(convertTime(visitTime))){
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                }else{
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }


    // ------------------------------------------- Search Brave history ---------------------------------------
    public static ArrayList<SiteHistory>  braveHistory(String choice) throws IOException, SQLException {
        listInfo.clear();
        Constant.getDates().clear();
        int numberVisitsByDate = 0;

        if(!Objects.equals(choice, "Display")){
            query = String.format(Constant.getBrave().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getBrave().getSqlCommand().get(0);
        }

        urlDatabase = Constant.getBrave().getDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString(Constant.getBrave().getSiteFields().get(0));
                String title = rs.getString(Constant.getBrave().getSiteFields().get(1));
                String visitTime = rs.getString(Constant.getBrave().getSiteFields().get(2));
                int visitCount = rs.getInt(Constant.getBrave().getSiteFields().get(3));
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);

                if(Constant.getDates().containsKey(convertTime(visitTime))){
                    numberVisitsByDate++;
                    Constant.setDates(convertTime(visitTime), numberVisitsByDate);
                }else{
                    Constant.setDates(convertTime(visitTime), 0);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        close();
        return listInfo;
    }
    
    // -------------------- Method to get all sites
    private static ArrayList<SiteHistory> getListSite() throws SQLException{
        ArrayList<SiteHistory> list = new ArrayList<SiteHistory>();

        try {  
            rs = stmt.executeQuery(Constant.getDashboard().getSqlCommand().get(3));  
              
            // loop through the result set  
            while (rs.next()) {  
                list.add(
                    new SiteHistory(rs.getString("url"), rs.getString("title"), rs.getInt("visit_count"))
                );

            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  

        return list;
    }

    private static boolean checkIfSiteInList(SiteHistory site) throws SQLException{
        for (SiteHistory el : getListSite()) {
            if(el.equals(site)){
                return true;
            }
        }
        return false;
    }

     public static String convertTime(String date){
        // Getting the current system time and passing it and passing the long value in the Date class
        if(date != null){
            Timestamp ts = new Timestamp(Long.parseLong(date));
            Date newDate = new Date(ts.getTime());
            String val = String.valueOf(newDate);
            
            String[] listDate = val.split(" ");
            String valDateString = String.format("%s %s %s", listDate[0], listDate[2], listDate[2]);
            
            return valDateString;
        }else {
            return "None";
        }
    }


    // Copy the database (To avoid an error like "database is locked")
    public static void copyDatabase(String path) throws IOException, SQLException {
        File source = new File(path);
        File destination = null;

        if (OSName.contains(Constant.getWindows().getName())){
            destination = new File("windowsDatabase.sqlite");
            Files.deleteIfExists(Path.of("windowsDatabase.sqlite"));
            Files.copy(source.toPath(), destination.toPath());

        } else if (Objects.equals(OSName, Constant.getLinux().getName())) {
            Files.deleteIfExists(Path.of("linuxDatabase.sqlite"));
            destination = new File("linuxDatabase.sqlite");
            Files.copy(source.toPath(), destination.toPath());
        }else {
            System.out.println("Other OS");
        }
    }

    
}
