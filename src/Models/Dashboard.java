package Models;

import java.util.ArrayList;

/**
 * The type Dashboard (model).
 */
public class Dashboard {
    private String name, iconSrc, databasePath;
    private ArrayList<String> sqlCommand, databasedFields;

    /**
     * Instantiates a new Dashboard.
     */
    public Dashboard() {}

    /**
     * Instantiates a new Dashboard.
     *
     * @param name            the name
     * @param iconSrc         the icon src
     * @param databasePath    the database path
     * @param sqlCommand      the sql command
     * @param databasedFields the databased fields
     */
    public Dashboard(String name, String iconSrc, String databasePath, ArrayList<String> sqlCommand,
            ArrayList<String> databasedFields) {
        this.name = name;
        this.iconSrc = iconSrc;
        this.databasePath = databasePath;
        this.sqlCommand = sqlCommand;
        this.databasedFields = databasedFields;
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
     * Gets databased fields.
     *
     * @return the databased fields
     */
    public ArrayList<String> getDatabasedFields() {
        return databasedFields;
    }

    /**
     * Sets databased fields.
     *
     * @param databasedFields the databased fields
     */
    public void setDatabasedFields(ArrayList<String> databasedFields) {
        this.databasedFields = databasedFields;
    }
}
