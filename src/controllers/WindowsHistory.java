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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        showDetails(listInfo);
        close();
    }

}
