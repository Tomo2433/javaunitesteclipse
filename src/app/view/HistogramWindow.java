package app.view;

import app.model.IntegerTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
/**
 * Program <code>MyWindow</code>
 * Klasa Klasa <code>HistogramWindow</code> definiujaca okno wykresu histogramu
 * @author T.Lech
 * @version 1.0	05/06/2023
 */
public class HistogramWindow extends JFrame {
    private HistogramDataset histogramDataset;
    private CenterPanel _centerPanel;
    private IntegerTableModel tableModel;
    private double[] tableValues;
    ChartPanel chartPanel;

    /**
     * kontruktor z parametrem
     * @param centerPanel
     */
    public HistogramWindow(CenterPanel centerPanel) {
        setTitle("Histogram");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
                .getResource("/resources/author_logo.png")));
        setSize(400, 300);

        _centerPanel = centerPanel;
        tableModel = _centerPanel.getTableModel();
        tableValues = tableModel.getDoubleValues();

        JFreeChart chart = getHistogramChart("Histogram", tableValues);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(300);
        chartPanel.setMaximumDrawWidth(300);

        // Tworzenie panelu wykresu i dodawanie go do okna
        chartPanel = new ChartPanel(chart);
        getContentPane().add(chartPanel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Metofa generująca wykres
     * @param name
     * @param dataArray
     * @return zwraca JfreeChart
     */
    private static JFreeChart getHistogramChart(String name, double[] dataArray)
    {
        String plotTitle = name;
        String xAxisLabel = "Komórka";
        String yAxis = "Wartość";
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (int i = 0; i < dataArray.length; i++)
        {
            dataSet.addValue(dataArray[i], (Integer) 1, (Integer) (i+1));
        }
        boolean legendshow = false;
        boolean toolTips = false;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createBarChart(plotTitle, xAxisLabel,
                yAxis, dataSet, orientation, legendshow, toolTips, urls);
        chart.setBackgroundPaint(Color.WHITE);

        // Set a very small font for the labels, and rotate them...
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 8));
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

        return chart;
    }
}
