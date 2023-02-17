package controllers;

import Models.SiteHistory;
import Views.HistoryView;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import Helper.Constant;


public class WindowsHistory extends HistoryView {

    // ------------------------------------ All variables -----------------------------------
    private static ArrayList<SiteHistory> listInfo = new ArrayList<>();
    private static Connection Conn;
    private static Statement stmt;
    private static ResultSet rs;
    private String username = System.getProperty("user.name");
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
    public void ChromeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            // query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
            query = String.format(Constant.getChrome().getSqlCommand().get(1), choice);
        }else {
            query = Constant.getChrome().getSqlCommand().get(0);
        }


        urlDatabase = Constant.getBraveDatabasePath();
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }


    // ------------------------------------------- Search Microsoft Edge history ---------------------------------------
    public void microsoftEdgeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = Constant.getMEdgeDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString("url");
                String title = rs.getString("title");
                String visitTime = rs.getString("last_visit_time");
                int visitCount = rs.getInt("visit_count");
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }


    // ------------------------------------------- Search Firefox history ---------------------------------------
    public void firefoxHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM moz_places WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM moz_places";
        }

        urlDatabase = Constant.getFirefoxDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString("url");
                String title = rs.getString("title");
                String visitTime = rs.getString("last_visit_date");
                int visitCount = rs.getInt("visit_count");
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }


    // ------------------------------------------- Search Opera history ---------------------------------------
    public void operaHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = Constant.getOperaDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString("url");
                String title = rs.getString("title");
                String visitTime = rs.getString("last_visit_time");
                int visitCount = rs.getInt("visit_count");
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }


    // ------------------------------------------- Search Vivaldi history ---------------------------------------
    public void vivaldiHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = Constant.getVivaldiDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString("url");
                String title = rs.getString("title");
                String visitTime = rs.getString("last_visit_time");
                int visitCount = rs.getInt("visit_count");
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }


    // ------------------------------------------- Search Brave history ---------------------------------------
    public void braveHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = Constant.getBraveDatabasePath();
        copyDatabase(String.format(urlDatabase, username));
        try {
            setConn();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                String url = rs.getString("url");
                String title = rs.getString("title");
                String visitTime = rs.getString("last_visit_time");
                int visitCount = rs.getInt("visit_count");
                String user = username;

                SiteHistory info = new SiteHistory(url, title, visitTime, visitCount, user);
                listInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }

}
