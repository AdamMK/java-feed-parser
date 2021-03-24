package com.feed;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    HierarchyDataParser p = new HierarchyDataParser();

    @Test
    void showMsgId(){
        String exampleLine = "|832|update|outcome|1616449515071|e9e4aa24-0026-413f-a91e-69b7962a91a6|57926669-c442-461f-9b7d-f74a1c8e3375|Draw|1/1|1|0|";
        assertEquals(List.of(832, "update", Instant.ofEpochSecond(1616449515071L) ), p.parse(exampleLine));
    }

}