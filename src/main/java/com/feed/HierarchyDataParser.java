package com.feed;


import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class HierarchyDataParser {


    public HierarchyData parse(String line)  {


        List<String> arrayLine = Arrays.asList(line.split("\\|"));
        int msgId = Integer.parseInt(arrayLine.get(1));
        String operation = arrayLine.get(2);
        Instant timestamp = Instant.ofEpochSecond(Long.parseLong(arrayLine.get(4)));
        String datatype = arrayLine.get(3);

//        if(datatype == "event"){
            String eventId = arrayLine.get(5);
            String category = arrayLine.get(6);
            String subCategory = arrayLine.get(7);
            String name = arrayLine.get(9) + arrayLine.get(10) + arrayLine.get(11);
            Instant startTime = Instant.ofEpochSecond(Long.parseLong(arrayLine.get(13)));
            boolean displayed = arrayLine.get(14).equals("1");
            boolean suspended = arrayLine.get(15).equals("1");

            Event hData = new Event(msgId, operation, timestamp, eventId, category, subCategory, name, startTime, displayed, suspended ) {};


        return hData;
    }

}
