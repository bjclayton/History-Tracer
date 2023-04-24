package Models;

/**
 * The type Tree node.
 * A class to manage a node on the tree
 * each node will have an icon and a value
 */

public class TreeNode {
    private String id, value, icon;

    /**
     * Instantiates a new Tree node.
     *
     * @param id    the id
     * @param value the value
     * @param icon  the icon
     */
    public TreeNode(String id, String value, String icon) {
        super();
        this.id = id;
        this.value = value;
        this.icon = icon;
    }

    /**
     * Instantiates a new Tree node.
     */
    public TreeNode() {
        super();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}
