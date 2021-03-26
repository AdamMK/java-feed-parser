package com.feed;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    HierarchyDataParser dataParser = new HierarchyDataParser();

    @Test
    void parseOutcome(){

        String exampleLine = "|832|update|outcome|1616449515071|e9e4aa24-0026-413f-a91e-69b7962a91a6|57926669-c442-461f-9b7d-f74a1c8e3375|Draw|1/1|1|0|";
        assertEquals("sds", dataParser.parse(exampleLine).toString());
    }

    @Test
    void parseEvent() {
        String exampleLine = "|1|create|event|1616768178080|ff9b822b-5645-423a-8fb1-6868eb210ccf|Football|Premier League|\\|Watford\\| vs \\|Southampton\\||1616768199536|0|1|";
        assertEquals(new HierarchyData(832, "update", Instant.ofEpochSecond(1616449515071L) ) {}, dataParser.parse(exampleLine).toString());
    }
}