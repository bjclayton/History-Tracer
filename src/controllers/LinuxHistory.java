package controllers;

import Views.HistoryView;
import Models.SiteHistory;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class LinuxHistory  extends HistoryView {

    // ------------------------------------ All variables -----------------------------------
    private static ArrayList<SiteHistory> listInfo = new ArrayList<>();
    private static ResultSet rs;
    private String username = System.getProperty("user.name");
    private static String database = "jdbc:sqlite:linuxDatabase.sqlite";
    private static String urlDatabase, query;
    private static Statement stmt;
    private static Connection Conn;


    // -------------------- Method to open connection
    public static void setConn(){
        try {
            Conn = DriverManager.getConnection(database);
            stmt = Conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // ------------- Method to close the connection
    public static void close() throws SQLException {Conn.close();}


    // ------- Method to Search Chrome's history ------------
    @Override
    public void ChromeHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }
        urlDatabase = "/home/%s/.config/google-chrome/Default/History";
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

    @Override
    public void microsoftEdgeHistory(String choice) {

    }


    // ------- Method to Search Firefox's history ------------
    @Override
    public void firefoxHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM moz_places WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM moz_places";
        }

        urlDatabase = "/home/%s/.mozilla/firefox/9rqyylkg.default-esr/places.sqlite";
        copyDatabase(String.format(urlDatabase,username));
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

    // ------- Method to Search Opera's history ------------
    @Override
    public void operaHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = "/home/%s/.config/opera/History";
        copyDatabase(String.format(urlDatabase,username));
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

    // ------- Method to Search Vivaldi's history ------------
    @Override
    public void vivaldiHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = "/home/%s/.config/vivaldi/Default/History";
        copyDatabase(String.format(urlDatabase,username));
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


    // ------- Method to Search Brave's history ------------
    @Override
    public void braveHistory(String choice) throws IOException, SQLException {
        listInfo.clear();

        if(!Objects.equals(choice, "Display")){
            query = "SELECT * FROM urls WHERE title LIKE '%" + choice + "%'";
        }else {
            query = "SELECT * FROM urls";
        }

        urlDatabase = "/home/%s/.config/BraveSoftware/Brave-Browser/Default/History";
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
