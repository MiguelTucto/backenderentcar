package com.upc.pe.backenderentcar.car.domain.persistence;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByYear(int year);
    Car findByAddress(String address);

    Page<Car> findCarByUserId(Long userId, Pageable pageable);
}
