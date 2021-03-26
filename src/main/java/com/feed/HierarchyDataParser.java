package com.feed;


import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class HierarchyDataParser {


    public Object parse(String line)  {

        List<String> arrayLine = Arrays.asList(line.split("\\|"));
        String datatype = arrayLine.get(3);
        System.out.println(arrayLine);
        System.out.println(arrayLine.size());

        try {
            switch (datatype) {
                case "outcome":
                    return parseOutcome(arrayLine);
                case "market":
                    return parseMarket(arrayLine);
                case "event":
                    return parseEvent(arrayLine);
            }
        } catch (Exception e){
            System.out.println("Message parsing exception");}

        return null;
    }


    public Event parseEvent(List<String> ar){
        int eIdx = 1;
        int msgId = Integer.parseInt(ar.get(eIdx++));
        String operation = ar.get(eIdx+=2);
        Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(eIdx++)));
        String eventId = ar.get(eIdx++);
        String category = ar.get(eIdx++);
        String subCategory = ar.get(eIdx++);
        String name = ar.get(eIdx++) + ar.get(eIdx++) + ar.get(eIdx++) + ar.get(eIdx+=2);
        Instant startTime = Instant.ofEpochSecond(Long.parseLong(ar.get(eIdx++)));
        boolean displayed = "1".equals(ar.get(eIdx++));
        boolean suspended = "1".equals(ar.get(eIdx));

        Event event = new Event(msgId, operation, timestamp, eventId, category, subCategory, name, startTime, displayed, suspended );
        return event;
    }

    public Market parseMarket(List<String> ar){
        int mIdx = 1;
        int msgId = Integer.parseInt(ar.get(mIdx++));
        String operation = ar.get(mIdx++);
        Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(mIdx++)));
        String eventId = ar.get(mIdx++);
        String marketId = ar.get(mIdx++);
        String name = ar.get(mIdx++);
        boolean displayed = "1".equals(ar.get(mIdx++));
        boolean suspended = "1".equals(ar.get(mIdx));

        Market market = new Market(msgId, operation, timestamp, eventId, marketId, name, displayed, suspended);
        return market;
    }

    public Outcome parseOutcome(List<String> ar){
        int oIdx = 1;
        int msgId = Integer.parseInt(ar.get(oIdx++));
        String operation = ar.get(oIdx+=2);
        Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(oIdx++)));
        String marketId = ar.get(oIdx++);
        String outcomeId = ar.get(oIdx++);
        String name = ar.get(oIdx++);
        String price = ar.get(oIdx++);
        boolean displayed = "1".equals(ar.get(oIdx++));
        boolean suspended = "1".equals(ar.get(oIdx));

        Outcome outcome = new Outcome(msgId, operation, timestamp, marketId, outcomeId, name, price, displayed, suspended);
        return outcome;
    }
}
