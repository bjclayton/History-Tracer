package Views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Helper.Constant;

/**
 * The type Popup help.
 */
public class PopupHelp extends JFrame {
    /**
     * The Panel.
     */
    JPanel panel;

    /**
     * Instantiates a new Popup help.
     */
    public PopupHelp(){
        setLayout(new GridBagLayout());

        // Create three JLabel components
        JLabel label1 = new JLabel("History Tracer v1.0", JLabel.CENTER);
        JLabel label2 = new JLabel("<html>Copyright © 2023 - Université Espoir de Calvary Chapel - Latremblay<html>", JLabel.CENTER);
        JLabel label3 = new JLabel("index.html", JLabel.CENTER);

        // Create a GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Set the fill and weight constraints to center the labels horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        // Set the anchor and insets constraints to center the labels vertically
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(3, 0, 3, 0);
        
        // Add the labels to the frame with the appropriate constraints
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(label1, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(label2, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(label3, gbc);

        label3.setForeground(Color.BLUE.darker());
        label3.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        label3.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    Desktop.getDesktop().open(new File("docs/index.html"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label3.setText("<html><a href=''>" + "index.html" + "</a></html>");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label3.setText("index.html");
            }
            
        });
 

        setSize(400,150);
        setTitle("History Tracer");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

