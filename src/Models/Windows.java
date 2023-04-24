package Models;

/**
 * The type Windows (model).
 */
public class Windows extends OS{

    /**
     * Instantiates a new Windows.
     */
    public Windows() {
    }

    /**
     * Instantiates a new Windows.
     *
     * @param name         the name
     * @param title        the title
     * @param iconSrc      the icon src
     * @param databaseNmae the database nmae
     */
    public Windows(String name, String title, String iconSrc, String databaseNmae) {
        super(name, title, iconSrc, databaseNmae);
    }   
}
