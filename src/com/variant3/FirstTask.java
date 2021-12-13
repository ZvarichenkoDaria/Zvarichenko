package com.variant3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.sqlite.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FirstTask {
    private static Connection connect;

    private static Float findAverageTicket(String sex, String place){
        try{
            var query = "SELECT * FROM passengers WHERE embarked = ? AND sex = ?";
            var statementCreate = connect.prepareStatement(query);
            statementCreate.setString(1, place);
            statementCreate.setString(2, sex);
            var ticket = statementCreate.executeQuery();

            var average = 0.0f;
            var numbers = 0;
            while (ticket.next()){
                average += ticket.getFloat("fare");
                numbers++;
            }

            return average / numbers;
        } catch (SQLException exception) {
            exception.getMessage();
        }
        return null;
    }

    public static void makeGraph() throws SQLException {

        try {
            DriverManager.registerDriver(new JDBC());
            connect = DriverManager.getConnection("jdbc:sqlite:titanic");
        } catch (SQLException exception) {
            exception.getMessage();
        }

        var dataset = new DefaultCategoryDataset();
        dataset.addValue(findAverageTicket("male", "S"), "Мужчина", "Саутгемптон");
        dataset.addValue(findAverageTicket("female", "S"), "Женщина" , "Саутгемптон");
        dataset.addValue(findAverageTicket("male", "C"), "Мужчина" , "Шербург");
        dataset.addValue(findAverageTicket("female", "C"), "Женщина", "Шербург");
        dataset.addValue(findAverageTicket("male", "Q"), "Мужчина" , "Куинстаун");
        dataset.addValue(findAverageTicket("female", "Q"), "Женщина" , "Куинстаун");

        var jFreeChart = ChartFactory.createBarChart("График средних цен", null, "График средних цен", dataset, PlotOrientation.VERTICAL, true,false, false);
        jFreeChart.setBackgroundPaint(Color.white);
        jFreeChart.getTitle().setPaint(Color.black);
        var categoryPlot = jFreeChart.getCategoryPlot();
        var barRenderer = (BarRenderer)categoryPlot.getRenderer();
        barRenderer.setItemMargin(0.0);

        var frame = new JFrame("График средних цен");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var panel = new ChartPanel(jFreeChart);
        var size = new Dimension();
        size.setSize(1400, 600);
        panel.setPreferredSize(size);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
