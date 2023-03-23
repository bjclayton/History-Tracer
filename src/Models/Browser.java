package Models;

import java.util.ArrayList;

public class Browser {
    private String name, iconSrc, databasePath, loginDatabasePath;
    private ArrayList<String> sqlCommand, siteFields, downloadFields, loginFields;

    public Browser(){}

    public Browser(String name, String iconSrc, String databasePath,  String loginDatabasePath, ArrayList<String> sqlCommand,
            ArrayList<String> siteFields, ArrayList<String> downloadFields, ArrayList<String> loginFields) {
        this.name = name;
        this.iconSrc = iconSrc;
        this.databasePath = databasePath;
        this.loginDatabasePath = loginDatabasePath;
        this.sqlCommand = sqlCommand;
        this.siteFields = siteFields;
        this.downloadFields = downloadFields;
        this.loginFields = loginFields;
    }

    public Browser(String name, String iconSrc, String databasePath, ArrayList<String> sqlCommand,
        ArrayList<String> siteFields, ArrayList<String> downloadFields, ArrayList<String> loginFields) {
        this.name = name;
        this.iconSrc = iconSrc;
        this.databasePath = databasePath;
        this.sqlCommand = sqlCommand;
        this.siteFields = siteFields;
        this.downloadFields = downloadFields;
        this.loginFields = loginFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getDatabasePath() {
        return databasePath;
    }

    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    public ArrayList<String> getSqlCommand() {
        return sqlCommand;
    }

    public void setSqlCommand(ArrayList<String> sqlCommand) {
        this.sqlCommand = sqlCommand;
    }

    public ArrayList<String> getSiteFields() {
        return siteFields;
    }

    public void setSiteFields(ArrayList<String> siteFields) {
        this.siteFields = siteFields;
    }

    public ArrayList<String> getDownloadFields() {
        return downloadFields;
    }

    public void setDownloadFields(ArrayList<String> downloadFields) {
        this.downloadFields = downloadFields;
    }

    public ArrayList<String> getLoginFields() {
        return loginFields;
    }

    public void setLoginFields(ArrayList<String> loginFields) {
        this.loginFields = loginFields;
    }

    public String getLoginDatabasePath() {
        return loginDatabasePath;
    }

    public void setLoginDatabasePath(String loginDatabasePath) {
        this.loginDatabasePath = loginDatabasePath;
    }
}
