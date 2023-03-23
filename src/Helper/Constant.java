package Helper;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Brave;
import Models.Browser;
import Models.Chrome;
import Models.Dashboard;
import Models.Downloads;
import Models.Firefox;
import Models.Icons;
import Models.Linux;
import Models.MicrosoftEdge;
import Models.Opera;
import Models.SiteHistory;
import Models.Table;
import Models.Vivaldi;
import Models.Windows;

public class Constant {
    private static Chrome chrome;
    private static MicrosoftEdge microsoftEdge;
    private static Firefox firefox;
    private static Opera opera;
    private static Vivaldi vivaldi;
    private static Brave brave;
    private static Browser browser;
    private static Windows windows;
    private static Linux linux;
    private static Icons icons;
    private static Table table;
    private static Dashboard dashboard;
    private static ArrayList<SiteHistory> listSites = new ArrayList<>();
    private static ArrayList<Downloads> downloadsData;
    private static String browserSelected;
    private static HashMap<String, Integer> dates = new HashMap<>();

    public static ArrayList<Downloads> getDownloadsData() {
        return downloadsData;
    }

    public static void setDownloadsData(ArrayList<Downloads> downloadsData) {
        Constant.downloadsData = downloadsData;
    }

    public static void setDates(HashMap<String, Integer> dates) {
        Constant.dates = dates;
    }

    public static Chrome getChrome() {
        return chrome;
    }

    public static void setChrome(Chrome chrome) {
        Constant.chrome = chrome;
    }

    public static MicrosoftEdge getMicrosoftEdge() {
        return microsoftEdge;
    }

    public static void setMicrosoftEdge(MicrosoftEdge microsoftEdge) {
        Constant.microsoftEdge = microsoftEdge;
    }

    public static Windows getWindows() {
        return windows;
    }

    public static void setWindows(Windows windows) {
        Constant.windows = windows;
    }

    public static Linux getLinux() {
        return linux;
    }

    public static void setLinux(Linux linux) {
        Constant.linux = linux;
    }

    public static Firefox getFirefox() {
        return firefox;
    }

    public static void setFirefox(Firefox firefox) {
        Constant.firefox = firefox;
    }

    public static Opera getOpera() {
        return opera;
    }

    public static void setOpera(Opera opera) {
        Constant.opera = opera;
    }

    public static Vivaldi getVivaldi() {
        return vivaldi;
    }

    public static void setVivaldi(Vivaldi vivaldi) {
        Constant.vivaldi = vivaldi;
    }

    public static Brave getBrave() {
        return brave;
    }

    public static void setBrave(Brave brave) {
        Constant.brave = brave;
    }

    public static Icons getIcons() {
        return icons;
    }

    public static void setIcons(Icons icons) {
        Constant.icons = icons;
    }

    public static Table getTable() {
        return table;
    }

    public static void setTable(Table table) {
        Constant.table = table;
    }

    public static Dashboard getDashboard() {
        return dashboard;
    }

    public static void setDashboard(Dashboard dashboard) {
        Constant.dashboard = dashboard;
    }

    public static ArrayList<SiteHistory> getListSites() {
        return listSites;
    }

    public static void setListSites(ArrayList<SiteHistory> listSites) {
        Constant.listSites = listSites;
    }

    public static String getBrowserSelected() {
        return browserSelected;
    }

    public static void setBrowserSelected(String browserSelected) {
        if(!browserSelected.equals("Dashboard")){
            Constant.browserSelected = browserSelected;
        }
    }

    public static HashMap<String, Integer> getDates() {
        return dates;
    }

    public static void setDates(String key, int value) {
        Constant.dates.put(key, value);
    }

    public static Browser getBrowser() {
        return browser;
    }

    public static void setBrowser(Browser browser) {
        Constant.browser = browser;
    }
}
