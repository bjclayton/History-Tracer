package Views;

import javax.swing.*;
import Helper.Constant;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.HistogramDataset;

public class DashboardView extends JFrame {

    public DashboardView(){
        setTitle("Dashboard");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getDashboard().getIconSrc());
        setIconImage(icon); // Add icon

        init();

        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void init() {
        this.getContentPane().setLayout(new GridBagLayout());

        //Setup panel
        JFreeChart chartMostVisited = createChart(createDataset(), "Most Visited Sites");
        ChartPanel panelMostVisited = new ChartPanel(chartMostVisited);

        JFreeChart chartLastWeek = createChart(createDataset(), "Last Week");
        ChartPanel panelLastWeek = new ChartPanel(chartLastWeek);
        
        JFreeChart chartTotalSites = createChart(createDataset(), "Total Sites");
        ChartPanel panelTotalSites = new ChartPanel(chartTotalSites);  
        
        JFreeChart hist = createHist();
        ChartPanel panelHist = new ChartPanel(hist);  

        this.getContentPane().add(panelMostVisited,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        this.getContentPane().add(panelLastWeek,  new GridBagConstraints(1, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        this.getContentPane().add(panelTotalSites,  new GridBagConstraints(2, 0, 1, 1, 1.0, 0.6, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));
        // //next row
        this.getContentPane().add(panelHist,  new GridBagConstraints(0, 1, 3, 1, 1.0, 0.4, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2, 2,
                2, 2), 0, 0));

        this.setPreferredSize(new Dimension(900, 550));
        this.pack();
    }


    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "IPhone 5s" , 20 );  
        dataset.setValue( "SamSung Grand" , 20 );   
        dataset.setValue( "MotoG" , 40 );    
        dataset.setValue( "Nokia Lumia" ,  10 );  
        return dataset;         
    }
     
    private static JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart(title,  dataset, false, false, false);
        return chart;
    }

    public static JFreeChart createHist() {
        double[] values = { 95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
                            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
                            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
                            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
                            77, 44, 58, 91, 10, 67, 57, 19, 88, 84                                
                          };
 
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);
 
        JFreeChart histogram = ChartFactory.createHistogram("Histogram", "Site", "Frequency", dataset, PlotOrientation.VERTICAL, false, false, false);
        return histogram;
    }
}
