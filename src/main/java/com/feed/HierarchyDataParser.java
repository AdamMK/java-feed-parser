package com.feed;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


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
            int msgId = Integer.parseInt(ar.get(1));
            String operation = ar.get(2);
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(4)));
            String eventId = ar.get(5);
            String category = ar.get(6);
            String subCategory = ar.get(7);
            String name =
                (ar.get(8) + ar.get(9) + ar.get(10) + ar.get(11) + ar.get(12)).replace("\\", "");
            Instant startTime = Instant.ofEpochSecond(Long.parseLong(ar.get(13)));
            boolean displayed = "1".equals(ar.get(14));
            boolean suspended = "1".equals(ar.get(15));

            return new Event(
                msgId,
                operation,
                timestamp,
                eventId,
                category,
                subCategory,
                name,
                startTime,
                displayed,
                suspended
            );
        } catch (Exception e) {
            throw new ParsingFailedException("Message could not be parsed " + e);
        }
    }

    private Market parseMarket(List<String> ar) throws ParsingFailedException {
        try {
            int msgId = Integer.parseInt(ar.get(1));
            String operation = ar.get(2);
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(4)));
            String eventId = ar.get(5);
            String marketId = ar.get(6);
            String name = ar.get(7);
            boolean displayed = "1".equals(ar.get(8));
            boolean suspended = "1".equals(ar.get(9));

            return new Market(msgId, operation, timestamp, eventId, marketId, name, displayed, suspended);
        } catch (Exception e) {
            throw new ParsingFailedException("Market could not be parsed");
        }
    }

    private Outcome parseOutcome(List<String> ar) throws ParsingFailedException {

        try {
            int msgId = Integer.parseInt(ar.get(1));
            String operation = ar.get(2);
            Instant timestamp = Instant.ofEpochSecond(Long.parseLong(ar.get(4)));
            String marketId = ar.get(5);
            String outcomeId = ar.get(6);
            String name = ar.get(7).equals("\\") ? (ar.get(7) + ar.get(8) + ar.get(9)).replace("\\", "") : (ar.get(7));
            String price = ar.get(7).equals("\\") ? ar.get(10) : ar.get(8);
            boolean displayed = ar.get(7).equals("\\") ? "1".equals(ar.get(11)) : "1".equals(ar.get(9));
            boolean suspended = ar.get(7).equals("\\") ? "1".equals(ar.get(12)) : "1".equals(ar.get(10));

            return new Outcome(msgId, operation, timestamp, marketId, outcomeId, name, price, displayed, suspended);
        } catch (Exception e) {
            throw new ParsingFailedException("Outcome could not be parsed");
        }
    }
}
