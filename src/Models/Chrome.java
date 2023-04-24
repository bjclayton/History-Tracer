package Models;

import java.util.ArrayList;

/**
 * The type Chrome (model).
 */
public class Chrome extends Browser{

    /**
     * Instantiates a new Chrome.
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
    public Chrome(String name, String iconSrc, String databasePath, String loginDatabasePath, ArrayList<String> sqlCommand,
            ArrayList<String> siteFields, ArrayList<String> downloadFields, ArrayList<String> loginFields) {
        super(name, iconSrc, databasePath, loginDatabasePath, sqlCommand, siteFields, downloadFields, loginFields);
    }

    /**
     * Instantiates a new Chrome.
     */
    public Chrome() {}
}
