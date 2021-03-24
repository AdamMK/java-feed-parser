package com.feed;


import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class HierarchyDataParser extends HierarchyData {


    public List<Object> parse(String line) {

        List<String> arrayLine = Arrays.asList(line.split("\\|"));
        msgId = Integer.parseInt(arrayLine.get(1));
        operation = arrayLine.get(2);
        timestamp = Instant.ofEpochSecond(Long.parseLong(arrayLine.get(4)));
        List<Object> head = Arrays.asList(msgId,operation,timestamp);
        return head;
    }

}
