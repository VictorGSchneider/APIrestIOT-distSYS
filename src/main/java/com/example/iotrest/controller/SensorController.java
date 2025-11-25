package com.example.iotrest.controller;

import com.example.iotrest.model.SensorData;
import com.example.iotrest.model.SensorSummary;
import com.example.iotrest.service.SensorDataService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private static final Logger log = LoggerFactory.getLogger(SensorController.class);

    private final SensorDataService sensorDataService;

    public SensorController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @PostMapping("/data")
    public ResponseEntity<String> receiveData(@Valid @RequestBody SensorData sensorData) {
        sensorDataService.save(sensorData);
        log.info("Dado recebido do sensor {}: tipo={}, valor={}, timestamp={}",
                sensorData.getSensorId(), sensorData.getType(), sensorData.getValue(), sensorData.getTimestamp());
        return ResponseEntity.ok("Data received");
    }

    @GetMapping("/data")
    public List<SensorData> listData() {
        return sensorDataService.findAll();
    }

    @GetMapping("/summary")
    public List<SensorSummary> summary() {
        return sensorDataService.getSummaries();
    }
}
