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

/**
 * The type Constant.
 * A class to manage all constants of the application
 */
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

    /**
     * Gets downloads data.
     *
     * @return the downloads data
     */
    public static ArrayList<Downloads> getDownloadsData() {
        return downloadsData;
    }

    /**
     * Sets downloads data.
     *
     * @param downloadsData the downloads data
     */
    public static void setDownloadsData(ArrayList<Downloads> downloadsData) {
        Constant.downloadsData = downloadsData;
    }

    /**
     * Sets dates.
     *
     * @param dates the dates
     */
    public static void setDates(HashMap<String, Integer> dates) {
        Constant.dates = dates;
    }

    /**
     * Gets chrome.
     *
     * @return the chrome
     */
    public static Chrome getChrome() {
        return chrome;
    }

    /**
     * Sets chrome.
     *
     * @param chrome the chrome
     */
    public static void setChrome(Chrome chrome) {
        Constant.chrome = chrome;
    }

    /**
     * Gets microsoft edge.
     *
     * @return the microsoft edge
     */
    public static MicrosoftEdge getMicrosoftEdge() {
        return microsoftEdge;
    }

    /**
     * Sets microsoft edge.
     *
     * @param microsoftEdge the microsoft edge
     */
    public static void setMicrosoftEdge(MicrosoftEdge microsoftEdge) {
        Constant.microsoftEdge = microsoftEdge;
    }

    /**
     * Gets windows.
     *
     * @return the windows
     */
    public static Windows getWindows() {
        return windows;
    }

    /**
     * Sets windows.
     *
     * @param windows the windows
     */
    public static void setWindows(Windows windows) {
        Constant.windows = windows;
    }

    /**
     * Gets linux.
     *
     * @return the linux
     */
    public static Linux getLinux() {
        return linux;
    }

    /**
     * Sets linux.
     *
     * @param linux the linux
     */
    public static void setLinux(Linux linux) {
        Constant.linux = linux;
    }

    /**
     * Gets firefox.
     *
     * @return the firefox
     */
    public static Firefox getFirefox() {
        return firefox;
    }

    /**
     * Sets firefox.
     *
     * @param firefox the firefox
     */
    public static void setFirefox(Firefox firefox) {
        Constant.firefox = firefox;
    }

    /**
     * Gets opera.
     *
     * @return the opera
     */
    public static Opera getOpera() {
        return opera;
    }

    /**
     * Sets opera.
     *
     * @param opera the opera
     */
    public static void setOpera(Opera opera) {
        Constant.opera = opera;
    }

    /**
     * Gets vivaldi.
     *
     * @return the vivaldi
     */
    public static Vivaldi getVivaldi() {
        return vivaldi;
    }

    /**
     * Sets vivaldi.
     *
     * @param vivaldi the vivaldi
     */
    public static void setVivaldi(Vivaldi vivaldi) {
        Constant.vivaldi = vivaldi;
    }

    /**
     * Gets brave.
     *
     * @return the brave
     */
    public static Brave getBrave() {
        return brave;
    }

    /**
     * Sets brave.
     *
     * @param brave the brave
     */
    public static void setBrave(Brave brave) {
        Constant.brave = brave;
    }

    /**
     * Gets icons.
     *
     * @return the icons
     */
    public static Icons getIcons() {
        return icons;
    }

    /**
     * Sets icons.
     *
     * @param icons the icons
     */
    public static void setIcons(Icons icons) {
        Constant.icons = icons;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public static Table getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public static void setTable(Table table) {
        Constant.table = table;
    }

    /**
     * Gets dashboard.
     *
     * @return the dashboard
     */
    public static Dashboard getDashboard() {
        return dashboard;
    }

    /**
     * Sets dashboard.
     *
     * @param dashboard the dashboard
     */
    public static void setDashboard(Dashboard dashboard) {
        Constant.dashboard = dashboard;
    }

    /**
     * Gets list sites.
     *
     * @return the list sites
     */
    public static ArrayList<SiteHistory> getListSites() {
        return listSites;
    }

    /**
     * Sets list sites.
     *
     * @param listSites the list sites
     */
    public static void setListSites(ArrayList<SiteHistory> listSites) {
        Constant.listSites = listSites;
    }

    /**
     * Gets browser selected.
     *
     * @return the browser selected
     */
    public static String getBrowserSelected() {
        return browserSelected;
    }

    /**
     * Sets browser selected.
     *
     * @param browserSelected the browser selected
     */
    public static void setBrowserSelected(String browserSelected) {
        if(!browserSelected.equals("Dashboard")){
            Constant.browserSelected = browserSelected;
        }
    }

    /**
     * Gets dates.
     *
     * @return the dates
     */
    public static HashMap<String, Integer> getDates() {
        return dates;
    }

    /**
     * Sets dates.
     *
     * @param key   the key
     * @param value the value
     */
    public static void setDates(String key, int value) {
        Constant.dates.put(key, value);
    }

    /**
     * Gets browser.
     *
     * @return the browser
     */
    public static Browser getBrowser() {
        return browser;
    }

    /**
     * Sets browser.
     *
     * @param browser the browser
     */
    public static void setBrowser(Browser browser) {
        Constant.browser = browser;
    }
}
