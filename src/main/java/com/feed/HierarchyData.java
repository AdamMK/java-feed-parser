package com.feed;

import java.time.Instant;


public abstract class HierarchyData {

    private int msgId;
    private String operation;
    private Instant timestamp;

    public HierarchyData(int msgId, String operation, Instant timestamp) {
        this.msgId = msgId;
        this.operation = operation;
        this.timestamp = timestamp;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "HierarchyData{" +
            "msgId=" + msgId +
            ", operation='" + operation + '\'' +
            ", timestamp=" + timestamp +
            '}';
    }
}
