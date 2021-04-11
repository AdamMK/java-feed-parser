package com.feed.repository;

import com.feed.Market;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarketDataRepository extends MongoRepository<Market, String> {

}
