package com.andrii.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sensor", schema = "coursework")
public class SensorData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @NotNull(message = "Missing temperature")
    @Column(name = "temperature")
    private String temperature;

    @Basic
    @NotNull(message = "Missing humidity")
    @Column(name = "humidity")
    private String humidity;

    public SensorData(String cityName, String humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorData sensorData = (SensorData) o;
        return Objects.equals(id, sensorData.id) && Objects.equals(temperature, sensorData.temperature) && Objects.equals(humidity, sensorData.humidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, humidity);
    }
}
