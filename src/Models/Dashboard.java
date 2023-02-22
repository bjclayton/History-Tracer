package Models;

import java.util.ArrayList;

public class Dashboard {
    private String name, iconSrc, databasePath;
    private ArrayList<String> sqlCommand, databasedFields;

    public Dashboard() {}

    public Dashboard(String name, String iconSrc, String databasePath, ArrayList<String> sqlCommand,
            ArrayList<String> databasedFields) {
        this.name = name;
        this.iconSrc = iconSrc;
        this.databasePath = databasePath;
        this.sqlCommand = sqlCommand;
        this.databasedFields = databasedFields;
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

    public ArrayList<String> getDatabasedFields() {
        return databasedFields;
    }

    public void setDatabasedFields(ArrayList<String> databasedFields) {
        this.databasedFields = databasedFields;
    }
}
