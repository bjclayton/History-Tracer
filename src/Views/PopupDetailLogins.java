
package Views;

import javax.swing.*;

import Helper.Constant;

import java.awt.*;

/**
 * The type Popup detail.
 * A class to display more detail about a line
 * when a user click on the JTable (Login)
 */

public class PopupDetailLogins  extends JFrame {

    /**
     * The Panel.
     */
    JPanel panel;
    private static JTextField  txtUrl;
    private static JTextField  txtUsername;

    /**
     * Instantiates a new Popup detail.
     */
    public PopupDetailLogins(){
        // create label
        JLabel lblTitle = new JLabel("Url");
        lblTitle.setBounds(50,50,150,50);

        JLabel lblUrl = new JLabel("Username");
        lblUrl.setBounds(50,100,150,50);


        // create TextField
        txtUrl= new JTextField();
        txtUrl.setBounds(170,65,300,25);

        txtUsername = new JTextField();
        txtUsername.setBounds(170,115,300,25);


        panel = new JPanel(); // create a panel to add label and button
        panel.setLayout(null);

        add(panel); // add panel on JFrame
        // add label and textField on panel
        panel.add(lblTitle);
        panel.add(lblUrl);
        panel.add(txtUrl);
        panel.add(txtUsername);

        setSize(500,300);
        setTitle("Proprieties");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets setTxtUrl.
     *
     * @param text the text
     */
    public static void setTxtUrl(String text) {
        txtUrl.setText(text);
    }

    /**
     * Sets setTxtUsername.
     *
     * @param text the text
     */
    public static void setTxtUsername(String text) {
        txtUsername.setText(text);
    }

}
