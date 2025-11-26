package com.example.iotrest.model;

public class SensorSummary {

    private final String type;
    private final long count;
    private final double average;

    public SensorSummary(String type, long count, double average) {
        this.type = type;
        this.count = count;
        this.average = average;
    }

    public String getType() {
        return type;
    }

    public long getCount() {
        return count;
    }

    public double getAverage() {
        return average;
    }
}
