package Models;

import java.util.ArrayList;

/**
 * The type Table (model).
 */
public class Table {
    /**
     * The Table site.
     */
    ArrayList<String> tableSite, /**
     * The Table download.
     */
    tableDownload, /**
     * The Table login.
     */
    tableLogin;

    /**
     * Instantiates a new Table.
     *
     * @param tableSite     the table site
     * @param tableDownload the table download
     * @param tableLogin    the table login
     */
    public Table(ArrayList<String> tableSite, ArrayList<String> tableDownload, ArrayList<String> tableLogin) {
        this.tableSite = tableSite;
        this.tableDownload = tableDownload;
        this.tableLogin = tableLogin;
    }

    /**
     * Gets table site.
     *
     * @return the table site
     */
    public ArrayList<String> getTableSite() {
        return tableSite;
    }

    /**
     * Sets table site.
     *
     * @param tableSite the table site
     */
    public void setTableSite(ArrayList<String> tableSite) {
        this.tableSite = tableSite;
    }

    /**
     * Gets table download.
     *
     * @return the table download
     */
    public ArrayList<String> getTableDownload() {
        return tableDownload;
    }

    /**
     * Sets table download.
     *
     * @param tableDownload the table download
     */
    public void setTableDownload(ArrayList<String> tableDownload) {
        this.tableDownload = tableDownload;
    }

    /**
     * Gets table login.
     *
     * @return the table login
     */
    public ArrayList<String> getTableLogin() {
        return tableLogin;
    }

    /**
     * Sets table login.
     *
     * @param tableLogin the table login
     */
    public void setTableLogin(ArrayList<String> tableLogin) {
        this.tableLogin = tableLogin;
    }

    
}
