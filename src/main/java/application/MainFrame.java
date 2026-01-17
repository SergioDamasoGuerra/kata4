package application;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import viewmodel.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static MainFrame create(){return new MainFrame();}

    private MainFrame () throws HeadlessException {
        this.setTitle("Histogram display");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainFrame display(Histogram histogram) {
        this.getContentPane().add(chartPanelWith(histogram));
        return this;
    }

    private ChartPanel chartPanelWith(Histogram histogram){
        return new ChartPanel(chartFor(histogram));
    }

    private JFreeChart chartFor(Histogram histogram) {
        return ChartFactory.createHistogram(
                histogram.title(),
                histogram.x(),
                histogram.y(),
                datasetOf(histogram)
        );
    }

    private XYSeriesCollection datasetOf(Histogram histogram) {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(seriesOf(histogram));
        return collection;
    }

    private XYSeries seriesOf(Histogram histogram) {
        XYSeries series = new XYSeries(histogram.legend());
        histogram.forEach(b -> series.add((double) b, histogram.count(b)));
        return series;
    }

}
