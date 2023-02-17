package Models;

public class OS {
    private String name, title, iconSrc, databaseName;

    public OS(){}

    public OS(String name){
        this.name = name;
    }
    
    public OS(String name, String title, String iconSrc, String databaseName) {
        this.name = name;
        this.title = title;
        this.iconSrc = iconSrc;
        this.databaseName = databaseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
