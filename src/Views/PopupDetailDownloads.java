
package Views;

import javax.swing.*;

import Helper.Constant;

import java.awt.*;

/**
 * The type Popup detail.
 * A class to display more detail about a line
 * when a user click on the JTable (Download)
 */

public class PopupDetailDownloads  extends JFrame {

    /**
     * The Panel.
     */
    JPanel panel;
    private static JTextField  txtReferrer;
    private static JTextField  txtCurrentPath;
    private static JTextField  txtTotalBytes;

    /**
     * Instantiates a new Popup detail.
     */
    public PopupDetailDownloads(){
        // create label
        JLabel lblTitle = new JLabel("Referrer");
        lblTitle.setBounds(50,50,150,50);

        JLabel lblUrl = new JLabel("Current Path");
        lblUrl.setBounds(50,100,150,50);

        JLabel lblTime = new JLabel("Total Bytes");
        lblTime.setBounds(50,150,150,50);


        // create TextField
        txtReferrer = new JTextField();
        txtReferrer.setBounds(170,65,300,25);

        txtCurrentPath = new JTextField();
        txtCurrentPath.setBounds(170,115,300,25);

        txtTotalBytes= new JTextField();
        txtTotalBytes.setBounds(170,165,300,25);

        panel = new JPanel(); // create a panel to add label and button
        panel.setLayout(null);

        add(panel); // add panel on JFrame
        // add label and textField on panel
        panel.add(lblTitle);
        panel.add(lblUrl);
        panel.add(lblTime);
        panel.add(txtReferrer);
        panel.add(txtCurrentPath);
        panel.add(txtTotalBytes);

        setSize(500,400);
        setTitle("Proprieties");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets txtReferrer.
     *
     * @param text the text
     */
    public static void setTxtReferrer(String text) {
        txtReferrer.setText(text);
    }

    /**
     * Sets txtCurrentPath.
     *
     * @param text the text
     */
    public static void setTxtCurrentPath(String text) {
        txtCurrentPath.setText(text);
    }

    /**
     * Sets txtTotalBytes.
     *
     * @param text the text
     */
    public static void setTxtTotalBytes(String text) {
        txtTotalBytes.setText(text);
    }
}
