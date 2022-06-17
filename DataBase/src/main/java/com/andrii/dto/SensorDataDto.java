package com.andrii.dto;

import com.andrii.models.SensorData;

public class SensorDataDto {
    private SensorData sensorData;

    public SensorDataDto(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public Integer getId() {
        return sensorData.getId();
    }

    public String getTemperature() {
        return sensorData.getTemperature();
    }

    public String getHumidity() {
        return sensorData.getHumidity();
    }
}
