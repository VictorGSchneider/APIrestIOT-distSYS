package com.example.iotrest.model;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SensorData {

    @NotBlank
    private String sensorId;

    @NotBlank
    private String type;

    @NotNull
    private Double value;

    @NotNull
    private Instant timestamp;

    public SensorData() {
    }

    public SensorData(String sensorId, String type, Double value, Instant timestamp) {
        this.sensorId = sensorId;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
