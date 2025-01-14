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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

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

/**
 * The type Dashboard view extends JFrame.
 * This class allows the user to obtain detailed reports on their digital presence by
 * providing statistics on the most visited websites, the sites where they spend the most
 * time, as well as a comparison with previous reports.
 */
public class DashboardView extends JFrame {

    private static ArrayList<SiteHistory> mostVisited = new ArrayList<>();
    private static double[] values;
    private static String choice = "";

    /**
     * Instantiates a new Dashboard view.
     *
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public DashboardView() throws IOException, SQLException {
        setTitle("Dashboard");

        if (Constant.getBrowserSelected() == null) {
            choice = "None";
        } else {
            choice = Constant.getBrowserSelected();
        }

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getDashboard().getIconSrc());
        setIconImage(icon); // Add icon

        init(choice);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * initialize the components for the dashboard.
     *
     * @param choice  the choice.
     * @throws   IOException
     * @throws  SQLException
     */
    private void init(String choice) throws IOException, SQLException {
        this.getContentPane().setLayout(new GridBagLayout());
        getMostvisitedSites();

        // Setup panel
        JFreeChart chartMostVisited = createChart(createDataset(choice));
        ChartPanel panelMostVisited = new ChartPanel(chartMostVisited);

        JFreeChart barMostVisited = createBar(CategoryDataset(choice, "No_Date"), "No_Date");
        ChartPanel panelbar = new ChartPanel(barMostVisited);

        JFreeChart dateMostVisited = createBar(CategoryDataset(choice, "Date"), "Date");
        ChartPanel paneldate = new ChartPanel(dateMostVisited);

        this.getContentPane().add(panelMostVisited,
                new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(2, 2,
                                2, 2),
                        0, 0));
        this.getContentPane().add(panelbar,
                new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(2, 2,
                                2, 2),
                        0, 0));
        this.getContentPane().add(paneldate,
                new GridBagConstraints(2, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(2, 2,
                                2, 2),
                        0, 0));

        this.setPreferredSize(new Dimension(1000, 550));
        this.pack();
    }


    private static PieDataset createDataset(String choice) throws IOException, SQLException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (SiteHistory site : mostVisited) {
            dataset.setValue(site.getTitle(), site.getVisitCount());
        }
        return dataset;
    }

    /**
     * Get data (type: categorical)
     *
     * @param choice
     * @param style
     * @return
     * @throws IOException
     * @throws SQLException
     */
    private static DefaultCategoryDataset CategoryDataset(String choice, String style)
            throws IOException, SQLException {
        if (style.equals("Date")) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Entry<String, Integer> e : getTopFiveDate(Constant.getDates()).entrySet()) {
                dataset.setValue(e.getValue(), "Visits", e.getKey());
            }
            return dataset;
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (SiteHistory site : mostVisited) {
            dataset.setValue(site.getVisitCount(), "Visits", site.getTitle());
        }
        return dataset;
    }

    /**
     * Create PieChart.
     *
     * @param dataset
     * @return
     */
    private static JFreeChart createChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("", dataset, false, false, false);
        return chart;
    }

    /**
     * Create the Bar.
     *
     * @param dataset
     * @param style
     * @return
     */
    private static JFreeChart createBar(DefaultCategoryDataset dataset, String style) {
        if (style.equals("Date")) {
            JFreeChart chart = ChartFactory.createLineChart(
                    "", // Chart title
                    "Date", // X-axis label
                    "Number of Visits", // Y-axis label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL, // Orientation
                    true, // Include legend
                    true, // Tooltips
                    false // URLs
            );

            /**
             * Set the chart background color
             */
            chart.setBackgroundPaint(Color.WHITE);

            // Customize the plot
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.GRAY);
            plot.setRangeGridlinePaint(Color.GRAY);

            /**
             * Set the X-axis label to rotate 90 degrees
             */
            CategoryAxis xAxis = plot.getDomainAxis();
            xAxis.setTickLabelFont(xAxis.getTickLabelFont().deriveFont(11f));
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

            return chart;
        }

        // Create the chart
        JFreeChart chart = ChartFactory.createBarChart(
                "", // Chart title
                "Website", // X-axis label
                "Number of Visits", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true, // Include legend
                true, // Tooltips
                false // URLs
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


    /**
     * Get 5 sites where the user spends the most time
     * @throws IOException
     * @throws SQLException
     */
    private static void getMostvisitedSites() throws IOException, SQLException {
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

    /**
     * Get data from the database.
     *
     * @param dataFromDb
     * @param topFive
     */
    private static void getTopFive(ArrayList<SiteHistory> dataFromDb, ArrayList<SiteHistory> topFive) {
        if (dataFromDb.size() >= 5) {
            for (int i = 0; i < 5; i++) {
                SiteHistory max = Collections.max(dataFromDb, new SiteHistory.ComparatorVisitCount());
                topFive.add(max);
                dataFromDb.remove(max);
            }

            values = new double[dataFromDb.size()];
            for (int i = 0; i < dataFromDb.size(); i++) {
                values[i] = dataFromDb.get(i).getVisitCount();
            }
        } else {
            for (int i = 0; i < dataFromDb.size(); i++) {
                SiteHistory max = Collections.max(dataFromDb, new SiteHistory.ComparatorVisitCount());
                topFive.add(max);
                dataFromDb.remove(max);
            }

            values = new double[dataFromDb.size()];
            for (int i = 0; i < dataFromDb.size(); i++) {
                values[i] = dataFromDb.get(i).getVisitCount();
            }
        }
    }


    /**
     * Find 5 days when the user spends more time on the internet
     *
     * @param data
     * @return
     */
    private static Map<String, Integer> getTopFiveDate(HashMap<String, Integer> data) {
        ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<>(data.entrySet());
        Collections.sort(entryList, Collections.reverseOrder(Map.Entry.comparingByValue()));
        Map<String, Integer> highestPairs = new LinkedHashMap<>();

        // get the highest five pairs
        if (data.size() >= 5) {
            for (int i = 0; i < 5 && i < entryList.size(); i++) {
                Map.Entry<String, Integer> entry = entryList.get(i);
                highestPairs.put(entry.getKey(), entry.getValue());
            }
        } else {
            for (int i = 0; i < entryList.size(); i++) {
                Map.Entry<String, Integer> entry = entryList.get(i);
                highestPairs.put(entry.getKey(), entry.getValue());
            }
        }

        return highestPairs;
    }
}