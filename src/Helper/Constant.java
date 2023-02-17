package Helper;

import Models.Brave;
import Models.Chrome;
import Models.Firefox;
import Models.Linux;
import Models.MicrosoftEdge;
import Models.Opera;
import Models.Vivaldi;
import Models.Windows;

public class Constant {
    private static Chrome chrome;
    private static MicrosoftEdge microsoftEdge;
    private static Firefox firefox;
    private static Opera opera;
    private static Vivaldi vivaldi;
    private static Brave brave;

    private static Windows windows;
    private static Linux linux;

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
}
