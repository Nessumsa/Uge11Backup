package com.example.uge11;

import java.io.File;
import java.util.List;

public class BucketID {

    File file;
    List<Record> data;
    public String getFilePath() {
        return file.getAbsolutePath();
    }


}
