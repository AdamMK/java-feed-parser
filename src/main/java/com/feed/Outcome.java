package com.feed;



import java.time.Instant;


public class Outcome extends HierarchyData{

    private String marketId;
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
}
