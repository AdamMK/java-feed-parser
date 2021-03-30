package com.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.net.SocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

@Component
public class DataConsumer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    HierarchyDataParser parser;

    @Autowired
    ObjectMapper jsonObjectMapper;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try (BufferedReader in = new BufferedReader(
            new InputStreamReader(SocketFactory.getDefault().createSocket("localhost", 8282).getInputStream()))){
                consume(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public String consume(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
//            parser.parse(line);

            System.out.println(line);
//            HierarchyData parsedValue = parser.parse(line);
//            jsonObjectMapper.writeValueAsString(parsedValue);

        }

        return content.toString();
    }

}
