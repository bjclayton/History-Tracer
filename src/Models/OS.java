package Models;

/**
 * The type Os (model).
 */
public class OS {
    private String name, title, iconSrc, databaseName;

    /**
     * Instantiates a new Os.
     */
    public OS(){}

    /**
     * Instantiates a new Os.
     *
     * @param name the name
     */
    public OS(String name){
        this.name = name;
    }

    /**
     * Instantiates a new Os.
     *
     * @param name         the name
     * @param title        the title
     * @param iconSrc      the icon src
     * @param databaseName the database name
     */
    public OS(String name, String title, String iconSrc, String databaseName) {
        this.name = name;
        this.title = title;
        this.iconSrc = iconSrc;
        this.databaseName = databaseName;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Gets database name.
     *
     * @return the database name
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Sets database name.
     *
     * @param databaseName the database name
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
