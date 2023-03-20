package Views;

import javax.swing.*;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class DashboardView extends JFrame {

    private static ArrayList<SiteHistory> mostVisited = new ArrayList<>();
    private static double[] values;
    private static String choice = "";


    public DashboardView() throws IOException, SQLException{
        setTitle("Dashboard");

        if(Constant.getBrowserSelected() == null){
            choice = "None";
        }else{
            choice = Constant.getBrowserSelected();
        }

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getDashboard().getIconSrc());
        setIconImage(icon); // Add icon

        init(choice);

        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void init(String choice) throws IOException, SQLException {
        this.getContentPane().setLayout(new GridBagLayout());
        getMostvisitedSites();

        //Setup panel
        JFreeChart chartMostVisited = createChart(createDataset(choice));
        ChartPanel panelMostVisited = new ChartPanel(chartMostVisited);

        JFreeChart barMostVisited = createBar(CategoryDataset(choice), "No_Date");
        ChartPanel panelbar = new ChartPanel(barMostVisited);

        JFreeChart dateMostVisited = createBar(CategoryDataset(choice), "Date");
        ChartPanel paneldate = new ChartPanel(dateMostVisited);
        
        

        this.getContentPane().add(panelMostVisited,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        this.getContentPane().add(panelbar,  new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        this.getContentPane().add(paneldate,  new GridBagConstraints(2, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));

        this.setPreferredSize(new Dimension(1000, 550));
        this.pack();
    }


    private static PieDataset createDataset(String choice) throws IOException, SQLException {        
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for (SiteHistory site : mostVisited) {
            dataset.setValue( site.getTitle() , site.getVisitCount());  
        }
        return dataset;         
    }

    private static DefaultCategoryDataset CategoryDataset(String choice) throws IOException, SQLException {        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (SiteHistory site : mostVisited) {
            dataset.setValue(site.getVisitCount(), "Visits", site.getTitle());
        }
        return dataset;         
    }
     
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("",  dataset, false, false, false);
        return chart;
    }

    private static JFreeChart createBar(DefaultCategoryDataset dataset, String style) {
        if(style.equals("Date")){
            JFreeChart chart = ChartFactory.createLineChart(
                "", // Chart title
                "Date",                         // X-axis label
                "Number of Visits",             // Y-axis label
                dataset,                        // Dataset
                PlotOrientation.VERTICAL,       // Orientation
                true,                           // Include legend
                true,                           // Tooltips
                false                           // URLs
            );
    
            // Set the chart background color
            chart.setBackgroundPaint(Color.WHITE);
    
            // Customize the plot
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.GRAY);
            plot.setRangeGridlinePaint(Color.GRAY);
    
            // Set the X-axis label to rotate 90 degrees
            CategoryAxis xAxis = plot.getDomainAxis();
            xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(11f));
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
    
            return chart;
        }

        // Create the chart
        JFreeChart chart = ChartFactory.createBarChart(
            "",    // Chart title
            "Website",                         // X-axis label
            "Number of Visits",                // Y-axis label
            dataset,                           // Dataset
            PlotOrientation.VERTICAL,          // Orientation
            true,                              // Include legend
            true,                              // Tooltips
            false                              // URLs
        );

        // Set the chart background color
        chart.setBackgroundPaint(Color.WHITE);

        // Customize the plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        return chart;
    }


    private static void getMostvisitedSites() throws IOException, SQLException{
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
        if(dataFromDb.size() >= 5){
            for (int i = 0; i < 5; i++) {
                SiteHistory max =  Collections.max(dataFromDb, new SiteHistory.ComparatorVisitCount());
                topFive.add(max);
                dataFromDb.remove(max);
            }
        

            values = new double[dataFromDb.size()];
            for (int i = 0; i < dataFromDb.size(); i++) {
                values[i] = dataFromDb.get(i).getVisitCount();
            }
        }else{
            for (int i = 0; i < dataFromDb.size(); i++) {
                SiteHistory max =  Collections.max(dataFromDb, new SiteHistory.ComparatorVisitCount());
                topFive.add(max);
                dataFromDb.remove(max);
            }
        

            values = new double[dataFromDb.size()];
            for (int i = 0; i < dataFromDb.size(); i++) {
                values[i] = dataFromDb.get(i).getVisitCount();
            }
        }
    }
}