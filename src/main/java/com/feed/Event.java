package com.feed;



import java.time.Instant;


public class Event extends HierarchyData{

    private String eventId;
    private String category;
    private String subCategory;
    private String name;
    private Instant startTime;
    private boolean displayed;
    private boolean suspended;

    public Event(
        int msgId,
        String operation,
        Instant timestamp,
        String eventId,
        String category,
        String subCategory,
        String name,
        Instant startTime,
        boolean displayed,
        boolean suspended
    ) {
        super(msgId, operation, timestamp);
        this.eventId = eventId;
        this.category = category;
        this.subCategory = subCategory;
        this.name = name;
        this.startTime = startTime;
        this.displayed = displayed;
        this.suspended = suspended;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
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
