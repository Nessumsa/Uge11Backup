package com.example.uge11;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HelloApplication extends Application {

    Scanner scanner = null;
    List<BucketID> buckets = new ArrayList<>();
    List<XYChart.Series> listOfSeries = new ArrayList<>();

    @Override public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xxAxis = new CategoryAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xxAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0.5);
        xAxis.setUpperBound(12);
        xAxis.setTickUnit(1);
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(500);
        xxAxis.setEndMargin(0.5);




        //defining a series
        //XYChart.Series series = new XYChart.Series();
        //XYChart.Series series2 = new XYChart.Series();
        //XYChart.Series series3 = new XYChart.Series();

        //series.setName("Bucket 1");
        //series2.setName("Bucket 2");
        //series3.setName("Bucket 3");
        //populating the series with data

        //series.getData().add(new XYChart.Data(1, 23));

        File directory = new File("C:/test/memeTest");

        for (File file : directory.listFiles()) {
            buckets.add(new BucketID(file));
            readContent(file);
            listOfSeries.add(new XYChart.Series());
        }


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
        for(int j = 0; j < buckets.size(); j++) {
            int tempLowerBound = 90001;
            int tempUpperBound = 0;
            for (int i = 1; i <= 12; i++) {
                int tempTotal = 0;
                for (Record s : buckets.get(j).getData()) {
                    if (s.month == i) {
                        tempTotal += Integer.parseInt((s.weight.split(" "))[0]);
                    }
                }
                System.out.println("Month: " + i + " total weight: " + tempTotal);
                //series.getData().add(new XYChart.Data(1, 23)
                listOfSeries.get(j).getData().add(new XYChart.Data(new DateFormatSymbols().getMonths()[i-1], tempTotal));
                if(tempTotal < tempLowerBound){
                    tempLowerBound = tempTotal;
                    yAxis.setLowerBound(tempTotal-500);
                }
                if(tempTotal > tempUpperBound){
                    tempUpperBound = tempTotal;
                    yAxis.setUpperBound(tempTotal+500);
                }
            }
            listOfSeries.get(j).setName((buckets.get(j).getFileName().split("[.]"))[0]);
        }

        Scene scene  = new Scene(lineChart,1400,600);
        for(XYChart.Series s : listOfSeries)
        {
            lineChart.getData().add(s);

        }



        stage.setScene(scene);
        stage.show();
    }
    private void readContent(File directory) {
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
                buckets.get(buckets.size()-1).setData(new Record(tempArray[0],
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