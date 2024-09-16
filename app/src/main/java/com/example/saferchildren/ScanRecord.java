package com.example.saferchildren;

public class ScanRecord {
    private String userId;
    private long timestamp;
    private String action;

    public ScanRecord() {} // Needed for Firestore

    public String getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }
}

