package com.feed;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HierarchyDataParserTest {

    HierarchyDataParser dataParser = new HierarchyDataParser();

    @Test
    void parseEvent() throws IncompatibleDatatypeException, ParsingFailedException {
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
                true,
                List.of()
                ), dataParser.parse(exampleLine));
    }

    @Test
    void parseMarket() throws IncompatibleDatatypeException, ParsingFailedException {
        String exampleLine = "|97|create|market|1616959321729|fe74a1cf-00b8-40f8-9d8b-f8493b7ffb3b|bc8b98c5-dcfe-4ad4-bba6-9a3a687df9ca|2nd Set Winner|0|1|";
        assertEquals(new Market(
                97,
                "create",
                Instant.ofEpochSecond(1616959321729L),
                "fe74a1cf-00b8-40f8-9d8b-f8493b7ffb3b",
                "bc8b98c5-dcfe-4ad4-bba6-9a3a687df9ca",
                "2nd Set Winner",
                false,
                true,
                List.of()
        ), dataParser.parse(exampleLine));
    }

    @Test
    void parseOutcomeDraw() throws IncompatibleDatatypeException, ParsingFailedException {

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

    @Test
    void parseOutcomeWin() throws IncompatibleDatatypeException, ParsingFailedException {

        String exampleLine = "|832|update|outcome|1616449515071|e9e4aa24-0026-413f-a91e-69b7962a91a6|57926669-c442-461f-9b7d-f74a1c8e3375|\\|Winner\\||1/1|1|0|";
        assertEquals(new Outcome(
            832,
            "update",
            Instant.ofEpochSecond(1616449515071L),
            "e9e4aa24-0026-413f-a91e-69b7962a91a6",
            "57926669-c442-461f-9b7d-f74a1c8e3375",
            "Winner",
            "1/1",
            true,
            false
        ), dataParser.parse(exampleLine));
    }

    @Test
    void failedToParseEvent() {
        String exampleMsg = "|1f|Football|event|\\|9536|0|1|\"";
        assertThrows(ParsingFailedException.class,() -> dataParser.parse(exampleMsg));
    }

    @Test
    void failedToParseMarket() {
        String exampleMsg = "|1f|Football|market|\\|Watford\\\\| vs \\\\|Southa6|0|1|\"";
        assertThrows(ParsingFailedException.class,() -> dataParser.parse(exampleMsg));
    }

    @Test
    void failedToParseOutcome() {
        String exampleMsg = "|1f|Football|outcome|\\|Wa\\|Southampton\\\\||1616768199536|0|1|";
        assertThrows(ParsingFailedException.class,() -> dataParser.parse(exampleMsg));
    }

    @Test
    void failedToRecogniseMessageDataType() throws IncompatibleDatatypeException, ParsingFailedException{
        String exampleMsg = "|832|update|unrecognised_type|";
        assertThrows(IncompatibleDatatypeException.class, () -> dataParser.parse(exampleMsg));
    }

}