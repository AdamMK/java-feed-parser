package com.feed.repository;

import com.feed.Outcome;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutcomeDataRepository extends MongoRepository<Outcome, String> {
}
