import Helper.Constant;
import Views.SplashScreen;
import controllers.LinuxHistory;
import controllers.WindowsHistory;

/**
 * History Tracer is a desktop application that reads history data from different
 * web browsers (Mozilla Firefox, Google Chrome, Microsoft Edge and Opera) and displays it in a table.
 * It also allows you to view all files downloaded by the user and bookmarks saved.
 * This tool analyzes the user's daily activity and then draws up a complete report on his digital presence.
 * This project was created with Java, JDK 18.0.2.1.
 *
 * @author  John Clayton Blanc
 * @version 1.0
 * @since   2023-04-25
 */

/**
 * The type Main.
 */
public class Main {
    private static String OSName;

    /**
     * The entry point of application.
     * Check the os and display the Splash Screen
     *
     * @param args the input arguments
     * @throws Throwable the throwable
     */
    public static void main(String[] args) throws Throwable {
        SplashScreen splash = new SplashScreen();
        try {
            Thread.sleep(3000);
            splash.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        OSName = System.getProperty("os.name"); // get the OS name

        // Check the os and display the screen
        if (OSName.contains(Constant.getWindows().getName())) {
            new WindowsHistory();
        } else if (OSName.contains(Constant.getLinux().getName())) {
            new LinuxHistory();
        } else {
            System.out.println("Other OS");
        }
    }
}
