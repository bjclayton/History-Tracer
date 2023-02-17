package Models;

import java.util.ArrayList;

public class Table {
    ArrayList<String> tableSite, tableDownload, tableLogin;

    public Table(ArrayList<String> tableSite, ArrayList<String> tableDownload, ArrayList<String> tableLogin) {
        this.tableSite = tableSite;
        this.tableDownload = tableDownload;
        this.tableLogin = tableLogin;
    }

    public ArrayList<String> getTableSite() {
        return tableSite;
    }

    public void setTableSite(ArrayList<String> tableSite) {
        this.tableSite = tableSite;
    }

    public ArrayList<String> getTableDownload() {
        return tableDownload;
    }

    public void setTableDownload(ArrayList<String> tableDownload) {
        this.tableDownload = tableDownload;
    }

    public ArrayList<String> getTableLogin() {
        return tableLogin;
    }

    public void setTableLogin(ArrayList<String> tableLogin) {
        this.tableLogin = tableLogin;
    }

    
}
