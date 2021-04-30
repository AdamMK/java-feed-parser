package com.feed.repository;

import com.feed.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDataRepository extends MongoRepository<Event, String> {

    Event findByEventId(String eventId);
}
