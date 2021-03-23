package com.feed;


import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class Parser {

    int msgId;
    String operation;
    String type;
    Long timestamp;
    Parser(){}

    public String splitter(String line){
        Parser header = new Parser();
        Gson gson = new Gson();
        List<String> arrayLine = Arrays.asList(line.split("\\|"));
        msgId = Integer.parseInt(arrayLine.get(1));
        operation = arrayLine.get(2);
        type = arrayLine.get(3);
        timestamp = Long.parseLong(arrayLine.get(4));
        String json = gson.toJson(header);
        return json;
    }

}
