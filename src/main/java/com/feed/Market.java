package com.feed;

import java.time.Instant;
import java.util.Objects;

public class Market extends HierarchyData{

    private String eventId;
    private String marketId;
    private String name;
    private boolean displayed;
    private boolean suspended;

    public Market(
        int msgId,
        String operation,
        Instant timestamp,
        String eventId,
        String marketId,
        String name,
        boolean displayed,
        boolean suspended
    ) {
        super(msgId, operation, timestamp);
        this.eventId = eventId;
        this.marketId = marketId;
        this.name = name;
        this.displayed = displayed;
        this.suspended = suspended;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Market{" +
                "eventId='" + eventId + '\'' +
                ", marketId='" + marketId + '\'' +
                ", name='" + name + '\'' +
                ", displayed=" + displayed +
                ", suspended=" + suspended +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return displayed == market.displayed && suspended == market.suspended && Objects.equals(eventId, market.eventId) && Objects.equals(marketId, market.marketId) && Objects.equals(name, market.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, marketId, name, displayed, suspended);
    }
}
