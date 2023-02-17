package Helper;

import Models.Chrome;
import Models.Linux;
import Models.Windows;

public class Constant {
    private static Chrome chrome;
    private static Windows windows;
    private static Linux linux;
    private static String chromeDatabasePath;
    private static String mEdgeDatabasePath;
    private static String firefoxDatabasePath;
    private static String operaDatabasePath;
    private static String vivaldiDatabasePath;
    private static String braveDatabasePath;

    public static String getChromeDatabasePath() {
        return chromeDatabasePath;
    }

    public static void setChromeDatabasePath(String chromeDatabasePath) {
        Constant.chromeDatabasePath = chromeDatabasePath;
    }

    public static String getMEdgeDatabasePath() {
        return Constant.mEdgeDatabasePath;
    }

    public static void setMEdgeDatabasePath(String mEdgeDatabasePath) {
        Constant.mEdgeDatabasePath = mEdgeDatabasePath;
    }

    public static String getFirefoxDatabasePath() {
        return Constant.firefoxDatabasePath;
    }

    public static void setFirefoxDatabasePath(String firefoxDatabasePath) {
        Constant.firefoxDatabasePath = firefoxDatabasePath;
    }

    public static String getOperaDatabasePath() {
        return Constant.operaDatabasePath;
    }

    public static void setOperaDatabasePath(String operaDatabasePath) {
        Constant.operaDatabasePath = operaDatabasePath;
    }

    public static String getVivaldiDatabasePath() {
        return Constant.vivaldiDatabasePath;
    }

    public static void setVivaldiDatabasePath(String vivaldiDatabasePath) {
        Constant.vivaldiDatabasePath = vivaldiDatabasePath;
    }

    public static String getBraveDatabasePath() {
        return Constant.braveDatabasePath;
    }

    public static void setBraveDatabasePath(String braveDatabasePath) {
        Constant.braveDatabasePath = braveDatabasePath;
    }

    public static Chrome getChrome() {
        return chrome;
    }

    public static void setChrome(Chrome chrome) {
        Constant.chrome = chrome;
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
}
