package Models;

import java.util.ArrayList;

public class Vivaldi extends Browser{

    public Vivaldi(String name, String iconSrc, String databasePath, ArrayList<String> sqlCommand,
            ArrayList<String> siteFields, ArrayList<String> downloadFields, ArrayList<String> loginFields) {
        super(name, iconSrc, databasePath, sqlCommand, siteFields, downloadFields, loginFields);
    }

    public Vivaldi() {
    }
}
