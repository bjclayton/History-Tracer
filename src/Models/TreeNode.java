package Models;

/*
A class to manage a node on the tree
each node will have an icon and a value
*/
public class TreeNode {

    private String id, value, icon;

    public TreeNode(String id, String value, String icon) {
        super();
        this.id = id;
        this.value = value;
        this.icon = icon;
    }

    public TreeNode() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
