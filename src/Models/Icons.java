package Models;

public class Icons {
    private String iconApp, iconCopy, iconSort, iconRefresh, iconSelectAll;

    public Icons(String iconApp, String iconCopy, String iconSort, String iconRefresh, String iconSelectAll) {
        this.iconApp = iconApp;
        this.iconCopy = iconCopy;
        this.iconSort = iconSort;
        this.iconRefresh = iconRefresh;
        this.iconSelectAll = iconSelectAll;
    }

    public String getIconApp() {
        return iconApp;
    }

    public void setIconApp(String iconApp) {
        this.iconApp = iconApp;
    }

    public String getIconCopy() {
        return iconCopy;
    }

    public void setIconCopy(String iconCopy) {
        this.iconCopy = iconCopy;
    }

    public String getIconSort() {
        return iconSort;
    }

    public void setIconSort(String iconSort) {
        this.iconSort = iconSort;
    }

    public String getIconRefresh() {
        return iconRefresh;
    }

    public void setIconRefresh(String iconRefresh) {
        this.iconRefresh = iconRefresh;
    }

    public String getIconSelectAll() {
        return iconSelectAll;
    }

    public void setIconSelectAll(String iconSelectAll) {
        this.iconSelectAll = iconSelectAll;
    }
}
