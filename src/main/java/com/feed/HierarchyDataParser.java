package com.feed;


import com.feed.Exception.IncompatibleDatatypeException;
import com.feed.Exception.ParsingFailedException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class HierarchyDataParser {


    public HierarchyData parse(String line) throws ParsingFailedException, IncompatibleDatatypeException {

        List<String> arrayLine = Arrays.asList(line.split("\\|"));
        String datatype = arrayLine.get(3);
        HierarchyData response;
        switch (datatype) {
            case "outcome":
                response = parseOutcome(arrayLine);
                break;
            case "market":
                response = parseMarket(arrayLine);
                break;
            case "event":
                response = parseEvent(arrayLine);
                break;
            default:
                throw new IncompatibleDatatypeException("Incompatible datatype exception: " + datatype);
        }
        return response;
    }


    private Event parseEvent(List<String> ar) throws ParsingFailedException {
        try {
            int eIdx = 1;
            int msgId = Integer.parseInt(ar.get(eIdx++));
            String operation = ar.get(eIdx += 2);
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(eIdx++)));
            String eventId = ar.get(eIdx++);
            String category = ar.get(eIdx++);
            String subCategory = ar.get(eIdx++);
            String name =
                (ar.get(eIdx++) + ar.get(eIdx++) + ar.get(eIdx++) + ar.get(eIdx++) + ar.get(eIdx++)).replace("\\", "");
            Instant startTime = Instant.ofEpochSecond(Long.parseLong(ar.get(eIdx++)));
            boolean displayed = "1".equals(ar.get(eIdx++));
            boolean suspended = "1".equals(ar.get(eIdx));

            Event event = new Event(msgId, operation, timestamp, eventId, category, subCategory, name, startTime, displayed, suspended );
            return event;
        } catch (Exception e){
            throw new ParsingFailedException("Message could not be parsed " + e);
        }
    }

    private Market parseMarket(List<String> ar) throws ParsingFailedException {
        try {
            int mIdx = 1;
            int msgId = Integer.parseInt(ar.get(mIdx++));
            String operation = ar.get(mIdx += 2);
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(mIdx++)));
            String eventId = ar.get(mIdx++);
            String marketId = ar.get(mIdx++);
            String name = ar.get(mIdx++);
            boolean displayed = "1".equals(ar.get(mIdx++));
            boolean suspended = "1".equals(ar.get(mIdx));

            Market market = new Market(msgId, operation, timestamp, eventId, marketId, name, displayed, suspended);
            return market;
        } catch (Exception e) {
            throw new ParsingFailedException("Market could not be parsed");
        }

    }

    private Outcome parseOutcome(List<String> ar) throws ParsingFailedException {
        try {
            int oIdx = 1;
            int msgId = Integer.parseInt(ar.get(oIdx++));
            String operation = ar.get(oIdx += 2);
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(oIdx++)));
            String marketId = ar.get(oIdx++);
            String outcomeId = ar.get(oIdx++);
            String name = ar.get(oIdx++);
            String price = ar.get(oIdx++);
            boolean displayed = "1".equals(ar.get(oIdx++));
            boolean suspended = "1".equals(ar.get(oIdx));

            Outcome outcome =
                new Outcome(msgId, operation, timestamp, marketId, outcomeId, name, price, displayed, suspended);
            return outcome;
        } catch (Exception e) {
            throw new ParsingFailedException("Outcome could not be parsed");
        }
    }
}
