package com.example.uge11;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BucketID {
    File file;
    List<Record> data = new ArrayList<>();
    public BucketID(File file) {
        this.file = file;
    }
    public String getFilePath() {
        return file.getAbsolutePath();
    }
    public void setData(Record data) {
        this.data.add(data);
    }
    public List<Record> getData() {
        return data;
    }
    public String getFileName(){
        return file.getName();
    }


}
