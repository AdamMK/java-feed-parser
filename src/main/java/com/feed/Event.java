package com.feed;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Objects;

@Document(collection = "event")
public class Event extends HierarchyData{

    @Id
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

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", displayed=" + displayed +
                ", suspended=" + suspended +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return displayed == event.displayed && suspended == event.suspended && Objects.equals(eventId, event.eventId) && Objects.equals(category, event.category) && Objects.equals(subCategory, event.subCategory) && Objects.equals(name, event.name) && Objects.equals(startTime, event.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, category, subCategory, name, startTime, displayed, suspended);
    }
}
