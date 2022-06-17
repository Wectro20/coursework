package com.andrii.repository;

import com.andrii.models.SensorData;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
}