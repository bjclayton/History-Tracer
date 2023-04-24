package Models;

import java.util.ArrayList;

/**
 * The type Firefox (model).
 */
public class Firefox extends Browser{

    /**
     * Instantiates a new Firefox.
     *
     * @param name           the name
     * @param iconSrc        the icon src
     * @param databasePath   the database path
     * @param sqlCommand     the sql command
     * @param siteFields     the site fields
     * @param downloadFields the download fields
     * @param loginFields    the login fields
     */
    public Firefox(String name, String iconSrc, String databasePath, ArrayList<String> sqlCommand,
            ArrayList<String> siteFields, ArrayList<String> downloadFields, ArrayList<String> loginFields) {
        super(name, iconSrc, databasePath, sqlCommand, siteFields, downloadFields, loginFields);
    }

    /**
     * Instantiates a new Firefox.
     */
    public Firefox() {
    }
}
