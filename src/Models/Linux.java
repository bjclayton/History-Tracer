package Models;

/**
 * The type Linux (model).
 */
public class Linux extends OS{

    /**
     * Instantiates a new Linux.
     */
    public Linux() {
    }

    /**
     * Instantiates a new Linux.
     *
     * @param name         the name
     * @param title        the title
     * @param iconSrc      the icon src
     * @param databaseNmae the database nmae
     */
    public Linux(String name, String title, String iconSrc, String databaseNmae) {
        super(name, title, iconSrc, databaseNmae);
    }
}
