package Models;

import java.util.ArrayList;

/**
 * The type Browser (model).
 */
public class Browser {
    private String name, iconSrc, databasePath, loginDatabasePath;
    private ArrayList<String> sqlCommand, siteFields, downloadFields, loginFields;

    /**
     * Instantiates a new Browser.
     */
    public Browser(){}

    /**
     * Instantiates a new Browser.
     *
     * @param name              the name
     * @param iconSrc           the icon src
     * @param databasePath      the database path
     * @param loginDatabasePath the login database path
     * @param sqlCommand        the sql command
     * @param siteFields        the site fields
     * @param downloadFields    the download fields
     * @param loginFields       the login fields
     */
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

    /**
     * Instantiates a new Browser.
     *
     * @param name           the name
     * @param iconSrc        the icon src
     * @param databasePath   the database path
     * @param sqlCommand     the sql command
     * @param siteFields     the site fields
     * @param downloadFields the download fields
     * @param loginFields    the login fields
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets icon src.
     *
     * @return the icon src
     */
    public String getIconSrc() {
        return iconSrc;
    }

    /**
     * Sets icon src.
     *
     * @param iconSrc the icon src
     */
    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    /**
     * Gets database path.
     *
     * @return the database path
     */
    public String getDatabasePath() {
        return databasePath;
    }

    /**
     * Sets database path.
     *
     * @param databasePath the database path
     */
    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    /**
     * Gets sql command.
     *
     * @return the sql command
     */
    public ArrayList<String> getSqlCommand() {
        return sqlCommand;
    }

    /**
     * Sets sql command.
     *
     * @param sqlCommand the sql command
     */
    public void setSqlCommand(ArrayList<String> sqlCommand) {
        this.sqlCommand = sqlCommand;
    }

    /**
     * Gets site fields.
     *
     * @return the site fields
     */
    public ArrayList<String> getSiteFields() {
        return siteFields;
    }

    /**
     * Sets site fields.
     *
     * @param siteFields the site fields
     */
    public void setSiteFields(ArrayList<String> siteFields) {
        this.siteFields = siteFields;
    }

    /**
     * Gets download fields.
     *
     * @return the download fields
     */
    public ArrayList<String> getDownloadFields() {
        return downloadFields;
    }

    /**
     * Sets download fields.
     *
     * @param downloadFields the download fields
     */
    public void setDownloadFields(ArrayList<String> downloadFields) {
        this.downloadFields = downloadFields;
    }

    /**
     * Gets login fields.
     *
     * @return the login fields
     */
    public ArrayList<String> getLoginFields() {
        return loginFields;
    }

    /**
     * Sets login fields.
     *
     * @param loginFields the login fields
     */
    public void setLoginFields(ArrayList<String> loginFields) {
        this.loginFields = loginFields;
    }

    /**
     * Gets login database path.
     *
     * @return the login database path
     */
    public String getLoginDatabasePath() {
        return loginDatabasePath;
    }

    /**
     * Sets login database path.
     *
     * @param loginDatabasePath the login database path
     */
    public void setLoginDatabasePath(String loginDatabasePath) {
        this.loginDatabasePath = loginDatabasePath;
    }
}
