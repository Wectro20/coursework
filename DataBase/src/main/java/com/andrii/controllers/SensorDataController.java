package com.andrii.controllers;

import com.andrii.dto.SensorDataDto;
import com.andrii.models.SensorData;
import com.andrii.service.SensorDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.andrii.exceptions.ConferenceNotFoundException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/sensor_data")
public class SensorDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorDataController.class);

    @Autowired
    private SensorDataService sensorDataService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<SensorDataDto> getSensor(@PathVariable(name = "id") Integer id) {
        if (sensorDataService.getSensorId(id) == null) {
            LOGGER.info("Can't update sensor with non-existing id" + id);
            throw new ConferenceNotFoundException("Sensor with id: " + id + " not found");
        }
        LOGGER.info("Successfully gave an object:" + id);
        SensorData sensorData = sensorDataService.getSensorId(id);
        return new ResponseEntity<SensorDataDto>(new SensorDataDto(sensorData), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SensorDataDto>> getSensor() {
        LOGGER.info("Successfully gave an objects");
        List<SensorData> sensorData = sensorDataService.getSensor();
        List<SensorDataDto> sensorsDto = new ArrayList<>();
        for (SensorData sd : sensorData) {
            SensorDataDto sensorDataDto = new SensorDataDto(sd);
            sensorsDto.add(sensorDataDto);
        }
        return new ResponseEntity<List<SensorDataDto>>(sensorsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SensorData> createSensor(@Valid @RequestBody SensorData sensorData) {
        LOGGER.info("Success added sensor");
        return new ResponseEntity<SensorData>(sensorDataService.addSensor(sensorData), HttpStatus.OK);
    }

    @PutMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SensorDataDto> updateSensor(@PathVariable("id")final int id, @Valid @RequestBody final SensorData sensorData) {
        if (sensorDataService.getSensorId(id) == null) {
            LOGGER.info("Can't update sensor without id - null value was passed instead of it");
            throw new ConferenceNotFoundException("Sensor with id: " + id + " not found");
        }
        LOGGER.info("Updated an sensor with id: " + id);
        sensorData.setId(id);
        sensorDataService.updateSensor(sensorData);
        return new ResponseEntity<SensorDataDto>(new SensorDataDto(sensorData), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SensorData> deleteSensor(@PathVariable("id") Integer id) {
        if (sensorDataService.getSensorId(id) == null) {
            LOGGER.info("Can't delete sensor ");
            throw new ConferenceNotFoundException("Sensor with id: " + id + " not found");
        }
        LOGGER.info("Successfully deleted sensor with id: " +id);
        sensorDataService.deleteSensorById(id);
        return ResponseEntity.noContent().build();
    }
}
