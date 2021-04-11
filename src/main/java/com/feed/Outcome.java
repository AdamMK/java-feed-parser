package com.feed;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Document(collection = "outcome")
public class Outcome extends HierarchyData{

    private String marketId;
    @Id
    private String outcomeId;
    private String name;
    private String price;
    private boolean displayed;
    private boolean suspended;

    public Outcome(
        int msgId,
        String operation,
        Instant timestamp,
        String marketId,
        String outcomeId,
        String name,
        String price,
        boolean displayed,
        boolean suspended
    ) {
        super(msgId, operation, timestamp);
        this.marketId = marketId;
        this.outcomeId = outcomeId;
        this.name = name;
        this.price = price;
        this.displayed = displayed;
        this.suspended = suspended;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(String outcomeId) {
        this.outcomeId = outcomeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isDisplayed() {
        return displayed;
    }

    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "marketId='" + marketId + '\'' +
                ", outcomeId='" + outcomeId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", displayed=" + displayed +
                ", suspended=" + suspended +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return displayed == outcome.displayed && suspended == outcome.suspended && Objects.equals(marketId, outcome.marketId) && Objects.equals(outcomeId, outcome.outcomeId) && Objects.equals(name, outcome.name) && Objects.equals(price, outcome.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketId, outcomeId, name, price, displayed, suspended);
    }
}
