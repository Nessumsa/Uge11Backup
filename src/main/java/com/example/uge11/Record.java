package com.example.uge11;

public class Record {

    String adress;
    String garbageType;
    int bucketID;
    String weight;
    int year;
    int month;
    int day;
    int hour;
    int minute;

    public Record(String adress,
                       String garbageType,
                       int bucketID,
                       String weight,
                       int year,
                       int month,
                       int day,
                       int hour,
                       int minute)
    {
        this.adress = adress;
        this.garbageType = garbageType;
        this.bucketID = bucketID;
        this.weight = weight;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }



}
