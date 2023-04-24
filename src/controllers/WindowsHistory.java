package controllers;

import Views.HistoryView;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import Helper.DashboardDatabase;
import Models.SiteHistory;


/**
 * The type Windows history.
 */
public class WindowsHistory extends HistoryView {

    public void ChromeHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.ChromeHistory(choice));
    }


    public void microsoftEdgeHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.microsoftEdgeHistory(choice));
    }


    public void firefoxHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.firefoxHistory(choice));
    }


    public void operaHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.operaHistory(choice));
    }


    public void vivaldiHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.vivaldiHistory(choice));
    }


    public void braveHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.braveHistory(choice));
    }


    @Override
    public void browserDownload(String name) throws IOException, SQLException {
        showDownloads(DashboardDatabase.browserDownload(name));
    }


    @Override
    public void browserLogins(String name) throws IOException, SQLException {
        showCredentials(DashboardDatabase.browserLogins(name));
    }


    @Override
    public void exportData(ArrayList<SiteHistory> data) {
        DashboardDatabase.exportData(data);
    }

}
