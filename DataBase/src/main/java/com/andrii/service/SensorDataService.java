package com.andrii.service;

import com.andrii.models.SensorData;
import com.andrii.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class SensorDataService {

    @Autowired
    public SensorDataRepository sensorDataRepository;

    public SensorData addSensor(final SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    public SensorData updateSensor(final SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    public List<SensorData> getSensor() {
        return sensorDataRepository.findAll();
    }

    public SensorData getSensorId(final Integer id) {
        return sensorDataRepository.findById(id).orElse(null);
    }

    public void deleteSensorById(Integer id){
        sensorDataRepository.deleteById(id);
    }
}