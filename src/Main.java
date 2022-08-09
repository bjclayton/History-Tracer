
import controllers.LinuxHistory;
import controllers.WindowsHistory;

import java.util.Objects;

public class Main {
    private static String OSName;

    public static void main(String[] args) {
        OSName = System.getProperty("os.name"); // get the OS name

        // Check the os and display his screen
        if (OSName.contains("Windows")){
            new WindowsHistory();
        } else if (OSName.contains("Linux")) {
            new LinuxHistory();
        }else {
            System.out.println("Other OS");
        }
    }
}
