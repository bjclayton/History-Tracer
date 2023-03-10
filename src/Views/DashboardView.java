package Views;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import Helper.Constant;
import Helper.DashboardDatabase;
import Models.SiteHistory;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.HistogramDataset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;


public class DashboardView extends JFrame {

    private static ArrayList<SiteHistory> mostVisited = new ArrayList<>();
    private static double[] values;
    private static String choice = "Chrome";


    public DashboardView() throws IOException, SQLException{
        setTitle("Dashboard");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getDashboard().getIconSrc());
        setIconImage(icon); // Add icon

        init(choice);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void init(String choice) throws IOException, SQLException {
        this.getContentPane().setLayout(new GridBagLayout());
        getMostvisitedSites(choice);

        //Setup panel
        JFreeChart chartMostVisited = createChart(createDataset(choice), "Most Visited Sites");
        ChartPanel panelMostVisited = new ChartPanel(chartMostVisited);

        JFreeChart chartLastWeek = createChart(createDataset(choice), "Last Week");
        ChartPanel panelLastWeek = new ChartPanel(chartLastWeek);
        
        JFreeChart chartTotalSites = createChart(createDataset(choice), "Total Sites");
        ChartPanel panelTotalSites = new ChartPanel(chartTotalSites);  
        
        JFreeChart hist = createHist();
        ChartPanel panelHist = new ChartPanel(hist);  
        
        JPanel panelBtn = new JPanel();
        panelBtn.add(createButton("Microsoft Edge"));
        panelBtn.add(createButton("Firefox"));
        panelBtn.add(createButton("Opera"));
        panelBtn.add(createButton("Vivaldi"));
        panelBtn.add(createButton("Brave"));
        panelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        // panelBtn.setPreferredSize(new Dimension(100, 100));
        // panelBtn.setMaximumSize(new Dimension(100, 100));
        System.out.println(choice);

        this.getContentPane().add(panelMostVisited,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        this.getContentPane().add(panelLastWeek,  new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        this.getContentPane().add(panelTotalSites,  new GridBagConstraints(2, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        // //next row
        this.getContentPane().add(panelHist,  new GridBagConstraints(0, 1, 3, 1, 1.0, 0.4, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        
        this.getContentPane().add(panelBtn,  new GridBagConstraints(0, 2, 3, 1, 1.0, 0.4, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
        2, 2), 0, 0));

        this.setPreferredSize(new Dimension(900, 550));
        this.pack();
    }


    private static PieDataset createDataset(String choice) throws IOException, SQLException {        
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for (SiteHistory site : mostVisited) {
            dataset.setValue( site.getTitle() , site.getVisitCount());  
        }
        return dataset;         
    }
     
    private static JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart(title,  dataset, false, false, false);
        return chart;
    }

    public static JFreeChart createHist() {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 10);
 
        JFreeChart histogram = ChartFactory.createHistogram("Histogram", "Site", "Frequency", dataset, PlotOrientation.VERTICAL, false, false, false);
        return histogram;
    }

    private static void getMostvisitedSites(String choice) throws IOException, SQLException{
        mostVisited.clear();
        values = new double[2];
        switch (choice) {
            case "Chrome":
                getTopFive(DashboardDatabase.ChromeHistory("Display"), mostVisited);
                break;
            case "Microsoft Edge":
                getTopFive(DashboardDatabase.microsoftEdgeHistory("Display"), mostVisited);
                break;
            case "Firefox":
                getTopFive(DashboardDatabase.firefoxHistory("Display"), mostVisited);
                break;
            case "Opera":
                getTopFive(DashboardDatabase.operaHistory("Display"), mostVisited);
                break;
            case "Vivaldi":
                getTopFive(DashboardDatabase.vivaldiHistory("Display"), mostVisited);
                break;
            case "Brave":
                getTopFive(DashboardDatabase.braveHistory("Display"), mostVisited);
                break;
        }
    }

    private static void getTopFive(ArrayList<SiteHistory> dataFromDb, ArrayList<SiteHistory> topFive){
        for (int i = 0; i < 5; i++) {
            SiteHistory max =  Collections.max(dataFromDb, new SiteHistory.ComparatorVisitCount());
            topFive.add(max);
            dataFromDb.remove(max);
        }

        values = new double[dataFromDb.size()];
        for (int i = 0; i < dataFromDb.size(); i++) {
            values[i] = dataFromDb.get(i).getVisitCount();
        }
    }


    private static JButton createButton(String texte){
        ImageIcon arrowIcon = null;
        JButton iconButton = null;
        switch (texte) {
            case "Chrome":
                arrowIcon = new ImageIcon(Constant.getChrome().getIconSrc());
                iconButton = new JButton(arrowIcon);
                iconButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        choice= "Chrome";
                    }
                });
                break;
            case "Microsoft Edge":
                arrowIcon = new ImageIcon(Constant.getMicrosoftEdge().getIconSrc());
                iconButton = new JButton(arrowIcon);
                iconButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        choice= "Microsoft Edge";
                    }
                });    
                break;
            case "Firefox":
                arrowIcon = new ImageIcon(Constant.getFirefox().getIconSrc());
                iconButton = new JButton(arrowIcon);
                iconButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        choice= "Firefox";
                    }
                });      
                break;
            case "Opera":
                arrowIcon = new ImageIcon(Constant.getOpera().getIconSrc());
                iconButton = new JButton(arrowIcon);
                iconButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        choice= "Opera";
                    }
                }); 
                break;
            case "Vivaldi":
                arrowIcon = new ImageIcon(Constant.getVivaldi().getIconSrc());
                iconButton = new JButton(arrowIcon);
                iconButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        choice= "Vivaldi";
                    }
                });  
                break;
            case "Brave":
                arrowIcon = new ImageIcon(Constant.getBrave().getIconSrc());
                iconButton = new JButton(arrowIcon);
                iconButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        choice= "Brave";
                    }
                }); 
                break;
        }
        
        iconButton.setText(texte);
        iconButton.setToolTipText(texte+" Statistics");
        iconButton.setVerticalTextPosition(AbstractButton.CENTER);
        iconButton.setHorizontalTextPosition(AbstractButton.LEADING); 
        iconButton.setMnemonic(KeyEvent.VK_I);
        return iconButton;
    }
}
