package controllers;

import Views.HistoryView;
import Models.SiteHistory;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import Helper.DashboardDatabase;

/**
 * The type Linux history.
 */
public class LinuxHistory extends HistoryView {

    /**
     * Search Chrome history.
     *
     * @param choice the choice
     * @throws IOException
     * @throws SQLException
     */
    public void ChromeHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.ChromeHistory(choice));
    }

    /**
     * Search Microsoft Edge history.
     *
     * @param choice the choice
     * @throws IOException
     * @throws SQLException
     */
    public void microsoftEdgeHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.microsoftEdgeHistory(choice));
    }

    /**
     * Search Firefox history.
     *
     * @param choice the choice
     * @throws IOException
     * @throws SQLException
     */
    public void firefoxHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.firefoxHistory(choice));
    }

    /**
     * Search Opera history.
     *
     * @param choice the choice
     * @throws IOException
     * @throws SQLException
     */
    public void operaHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.operaHistory(choice));
    }

    /**
     * Search Vivaldi history.
     *
     * @param choice the choice
     * @throws IOException
     * @throws SQLException
     */
    public void vivaldiHistory(String choice) throws IOException, SQLException {
        showDetails(DashboardDatabase.vivaldiHistory(choice));
    }

    /**
     * Search Brave history.
     *
     * @param choice the choice
     * @throws IOException
     * @throws SQLException
     */
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