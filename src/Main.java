import Helper.Constant;
import Views.SplashScreen;
import controllers.LinuxHistory;
import controllers.WindowsHistory;

public class Main {
    private static String OSName;


    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen();
        try {
            Thread.sleep(10000);
            splash.dispose();
        } catch(Exception e) {
            e.printStackTrace();
        }

        OSName = System.getProperty("os.name"); // get the OS name

        // Check the os and display the screen
        if (OSName.contains(Constant.getWindows().getName())){
            new WindowsHistory();
        } else if (OSName.contains(Constant.getLinux().getName())) {
            new LinuxHistory();
        }else {
            System.out.println("Other OS");
        }
    }
}
