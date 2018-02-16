package com.codecool.githubParser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.util.List;

public class PieChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public PieChart(String applicationTitle, String chartTitle, String set) {
        super(applicationTitle);

        // This will create the dataset
        PieDataset dataset = createDataset(set);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset
     */
    private  PieDataset createDataset(String set) {
        GithubStat githubStat = GithubStatParser.getGithubStat("bar-recommender");
        List<Contributor> contributorList = githubStat.getContributorList();
        for (Contributor contributor: contributorList) {
            System.out.println(contributor.getUserName() + " commits: " + contributor.getCommits() + " additions: " + contributor.getAdditions() + " deletions: " + contributor.getDeletion() + " total: " + contributor.getTotalAdditions());
        }
        DefaultPieDataset result = new DefaultPieDataset();
        if (set.equals("commit")) {
            for (Contributor contr: contributorList) {
                result.setValue(contr.getUserName(), contr.getCommits());
            }
        }
        else if (set.equals("total")) {
            for (Contributor contr: contributorList) {
                result.setValue(contr.getUserName(), contr.getTotalAdditions());
            }
        }

        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title,                  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
}