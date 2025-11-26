package com.example.iotrest.service;

import com.example.iotrest.model.SensorData;
import com.example.iotrest.model.SensorSummary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class SensorDataService {

    private final List<SensorData> receivedData = new CopyOnWriteArrayList<>();

    public void save(SensorData sensorData) {
        receivedData.add(sensorData);
    }

    public List<SensorData> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(receivedData));
    }

    public List<SensorSummary> getSummaries() {
        Map<String, List<SensorData>> grouped = receivedData.stream()
                .collect(Collectors.groupingBy(SensorData::getType));

        return grouped.entrySet().stream()
                .map(entry -> {
                    double average = entry.getValue().stream()
                            .collect(Collectors.averagingDouble(SensorData::getValue));
                    return new SensorSummary(entry.getKey(), entry.getValue().size(), average);
                })
                .sorted((a, b) -> a.getType().compareToIgnoreCase(b.getType()))
                .toList();
    }
}
