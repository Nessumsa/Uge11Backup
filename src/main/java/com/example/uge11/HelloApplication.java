package com.example.uge11;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HelloApplication extends Application {

    Scanner scanner = null;
    List<Record> data = new ArrayList<>();

    @Override public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0.5);
        xAxis.setUpperBound(12);
        xAxis.setTickUnit(1);


        //defining a series
        XYChart.Series series = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series.setName("Bucket 1");
        series2.setName("Bucket 2");
        series3.setName("Bucket 3");
        //populating the series with data

        //series.getData().add(new XYChart.Data(1, 23));

        File directory = new File("C:/test/memeTest/Vejlevej_129.txt");


        /*scanner = new Scanner(directory);
        if(scanner.hasNextLine())
        {
            scanner.nextLine();
        }
        while(scanner.hasNextLine())
        {

            String line = scanner.nextLine();
            String[] tempArray = line.split("[:,]");
            for (int i = 0; i < tempArray[0].length(); i++) {
                System.out.println(tempArray[8]);
            }
            data.add(new Record(tempArray[0],
                            tempArray[1],
                            Integer.parseInt(tempArray[2]),
                            tempArray[3],
                            Integer.parseInt(tempArray[4]),
                            Integer.parseInt(tempArray[5]),
                            Integer.parseInt(tempArray[6]),
                            Integer.parseInt(tempArray[7]),
                            Integer.parseInt(tempArray[8]))
            );
        }*/
        for(int i = 1; i <= 12; i++)
        {
            int tempTotal = 0;
            for(Record s : data)
            {
                if(s.month == i)
                {
                    tempTotal += Integer.parseInt((s.weight.split(" "))[0]);
                }
            }
            System.out.println("Month: " + i + " total weight: "+ tempTotal);
            //series.getData().add(new XYChart.Data(1, 23)
            series.getData().add(new XYChart.Data(i, tempTotal));
        }


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);


        stage.setScene(scene);
        stage.show();
    }
    private void ReadContent(File directory) {
        try  {
            scanner = new Scanner(directory);
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }
            while(scanner.hasNextLine())
            {

                String line = scanner.nextLine();
                String[] tempArray = line.split("[:,]");
                for (int i = 0; i < tempArray[0].length(); i++) {
                    System.out.println(tempArray[8]);
                }
                data.add(new Record(tempArray[0],
                        tempArray[1],
                        Integer.parseInt(tempArray[2]),
                        tempArray[3],
                        Integer.parseInt(tempArray[4]),
                        Integer.parseInt(tempArray[5]),
                        Integer.parseInt(tempArray[6]),
                        Integer.parseInt(tempArray[7]),
                        Integer.parseInt(tempArray[8]))
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}