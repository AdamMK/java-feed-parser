package com.feed;

import com.feed.Exception.IncompatibleDatatypeException;
import com.feed.Exception.ParsingFailedException;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    HierarchyDataParser dataParser = new HierarchyDataParser();

    @Test
    void parseEvent() throws IncompatibleDatatypeException {
        String exampleLine = "|1|create|event|1616768178080|ff9b822b-5645-423a-8fb1-6868eb210ccf|Football|Premier League|\\|Watford\\| vs \\|Southampton\\||1616768199536|0|1|";
        assertEquals(new Event(
                1,
                "update",
                Instant.ofEpochSecond(1616768178080L),
                "ff9b822b-5645-423a-8fb1-6868eb210ccf",
                "Football",
                "Premier League",
                "Watford vs Southampton",
                Instant.ofEpochSecond(1616768199536L),
                false,
                true
                ), dataParser.parse(exampleLine));
    }

    @Test
    void parseMarket() throws IncompatibleDatatypeException {
        String exampleLine = "|97|create|market|1616959321729|fe74a1cf-00b8-40f8-9d8b-f8493b7ffb3b|bc8b98c5-dcfe-4ad4-bba6-9a3a687df9ca|2nd Set Winner|0|1|";
        assertEquals(new Market(
                97,
                "create",
                Instant.ofEpochSecond(1616959321729L),
                "fe74a1cf-00b8-40f8-9d8b-f8493b7ffb3b",
                "bc8b98c5-dcfe-4ad4-bba6-9a3a687df9ca",
                "2nd Set Winner",
                false,
                true
        ), dataParser.parse(exampleLine));
    }

    @Test
    void parseOutcome() throws IncompatibleDatatypeException {

        String exampleLine = "|832|update|outcome|1616449515071|e9e4aa24-0026-413f-a91e-69b7962a91a6|57926669-c442-461f-9b7d-f74a1c8e3375|Draw|1/1|1|0|";
        assertEquals(new Outcome(
                832,
                "update",
                Instant.ofEpochSecond(1616449515071L),
                "e9e4aa24-0026-413f-a91e-69b7962a91a6",
                "57926669-c442-461f-9b7d-f74a1c8e3375",
                "Draw",
                "1/1",
                true,
                false
        ), dataParser.parse(exampleLine));
    }

//    @Test
//    void failedToParseEvent() throws IncompatibleDatatypeException {
//        String exampleMsg = "|1|create|event|1616178080|ff9b822b-5645-423a-8fb1-6868eb210ccf|Football|Premier League|\\|Watford\\\\| vs \\\\|Southampton\\\\||1616768199536|0|1|\"";
//        assertEquals(new ParsingFailedException("sadasd"),dataParser.parse(exampleMsg));
//    }

}