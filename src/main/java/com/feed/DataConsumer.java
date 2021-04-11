package com.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feed.repository.EventDataRepository;
import com.feed.repository.MarketDataRepository;
import com.feed.repository.OutcomeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.net.SocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;


@Component
public class DataConsumer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    HierarchyDataParser parser;

    @Autowired
    ObjectMapper jsonObjectMapper;

    @Autowired
    EventDataRepository eventDataRepository;

    @Autowired
    MarketDataRepository marketDataRepository;

    @Autowired
    OutcomeDataRepository outcomeDataRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try (BufferedReader in = new BufferedReader(
            new InputStreamReader(SocketFactory.getDefault().createSocket("localhost", 8282).getInputStream()))){
                consume(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static final Logger LOGGER = Logger.getLogger(DataConsumer.class.getName());

    public String consume(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());

            //System.out.println(line);

            //assumption on how app would behave on partial data corruption
            try {
                HierarchyData parsedValue = parser.parse(line);
                if (parsedValue instanceof Event)
                    eventDataRepository.save((Event) parsedValue);
                else if (parsedValue instanceof Market)
                    marketDataRepository.save((Market) parsedValue);
                else if (parsedValue instanceof Outcome)
                    outcomeDataRepository.save((Outcome) parsedValue);
                //System.out.println(jsonObjectMapper.writeValueAsString(parsedValue));
            } catch (Exception e) {
                LOGGER.info("Message could not be parsed");
            }

        }

        return content.toString();
    }

}
