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

        if(datatype.equals("event")){
            String eventId = arrayLine.get(5);
            String category = arrayLine.get(6);
            String subCategory = arrayLine.get(7);
            String name = arrayLine.get(9) + arrayLine.get(10) + arrayLine.get(11);
            Instant startTime = Instant.ofEpochSecond(Long.parseLong(arrayLine.get(13)));
            boolean displayed = arrayLine.get(14).equals("1");
            boolean suspended = arrayLine.get(15).equals("1");

            return new Event(msgId, operation, timestamp, eventId, category, subCategory, name, startTime, displayed, suspended );

        } else if(datatype.equals("market")){
            String eventId = arrayLine.get(5);
            String marketId = arrayLine.get(6);
            String name = arrayLine.get(8);
            boolean displayed = arrayLine.get(9).equals("1");
            boolean suspended = arrayLine.get(10).equals("1");

            return new Market(msgId, operation, timestamp, eventId, marketId, name, displayed, suspended);
        } else {
            String marketId = arrayLine.get(5);
            String outcomeId = arrayLine.get(6);
            String name = arrayLine.get(8);
            String price = arrayLine.get(9) + arrayLine.get(10);;
            boolean displayed = arrayLine.get(11).equals("1");
            boolean suspended = arrayLine.get(12).equals("1");

            return new Outcome(msgId, operation, timestamp, marketId, outcomeId, name, price, displayed, suspended);
        }
        return eventData;
    }

}
