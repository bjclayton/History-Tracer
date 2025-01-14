package Views;

import javax.swing.*;

import Helper.Constant;

import java.awt.*;

/**
 * The type Popup detail.
 * A class to display more detail about a line
 * when a user click on the JTable
 */

public class PopupDetail  extends JFrame {

    /**
     * The Panel.
     */
    JPanel panel;
    private static JTextField  txtTitle;
    private static JTextField  txtUrl;
    private static JTextField  txtTime;
    private static JTextField  txtVisitCount;

    /**
     * Instantiates a new Popup detail.
     */
    public PopupDetail(){
        // create label
        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(50,50,150,50);

        JLabel lblUrl = new JLabel("Url");
        lblUrl.setBounds(50,100,150,50);

        JLabel lblTime = new JLabel("Last visit time");
        lblTime.setBounds(50,150,150,50);

        JLabel lblVisitCount = new JLabel("Visit count");
        lblVisitCount.setBounds(50,200,150,50);

        // create TextField
        txtTitle = new JTextField();
        txtTitle.setBounds(170,65,300,25);

        txtUrl= new JTextField();
        txtUrl.setBounds(170,115,300,25);

        txtTime= new JTextField();
        txtTime.setBounds(170,165,300,25);

        txtVisitCount = new JTextField();
        txtVisitCount.setBounds(170,215,300,25);

        panel = new JPanel(); // create a panel to add label and button
        panel.setLayout(null);

        add(panel); // add panel on JFrame
        // add label and textField on panel
        panel.add(lblTitle);
        panel.add(lblUrl);
        panel.add(lblTime);
        panel.add(lblVisitCount);
        panel.add(txtTitle);
        panel.add(txtUrl);
        panel.add(txtTime);
        panel.add(txtVisitCount);

        setSize(500,400);
        setTitle("Proprieties");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets txt title.
     *
     * @param text the text
     */
    public static void setTxtTitle(String text) {
        txtTitle.setText(text);
    }

    /**
     * Sets txt url.
     *
     * @param text the text
     */
    public static void setTxtUrl(String text) {
        txtUrl.setText(text);
    }

    /**
     * Sets txt time.
     *
     * @param text the text
     */
    public static void setTxtTime(String text) {
        txtTime.setText(text);
    }

    /**
     * Sets txt visit count.
     *
     * @param text the text
     */
    public static void setTxtVisitCount(String text) {
        txtVisitCount.setText(text);
    }
}
