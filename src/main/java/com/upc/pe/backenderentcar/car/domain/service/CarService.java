package com.upc.pe.backenderentcar.car.domain.service;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    List<Car> getAll();
    Page<Car> getAll(Pageable pageable);
    Car getById(Long carId);
    Car create(Car car);
    Car update(Long carId, Car request);
    ResponseEntity<?> delete(Long carId);
}
