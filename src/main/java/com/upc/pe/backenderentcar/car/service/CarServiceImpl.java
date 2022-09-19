package com.upc.pe.backenderentcar.car.service;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.car.domain.persistence.CarRepository;
import com.upc.pe.backenderentcar.car.domain.service.CarService;
import com.upc.pe.backenderentcar.shared.exception.ResourceNotFoundException;
import com.upc.pe.backenderentcar.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {
    private static final String ENTITY = "Car";
    private final CarRepository carRepository;
    private final Validator validator;

    public CarServiceImpl(CarRepository carRepository, Validator validator) {
        this.carRepository = carRepository;
        this.validator = validator;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Page<Car> getAll(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Car getById(Long carId) {
        return carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, carId));
    }

    @Override
    public Car create(Car car) {
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return carRepository.save(car);
    }

    @Override
    public Car update(Long carId, Car request) {
        Set<ConstraintViolation<Car>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return carRepository.findById(carId).map(car ->
                carRepository.save(car
                        .withAddress(request.getAddress())
                        .withCarValueInDollars(request.getCarValueInDollars()))
                        .withManual(request.isManual())
                        .withBrand(request.getBrand())
                        .withYear(request.getYear())
                        .withModel(request.getModel())
                        .withMileage(request.getMileage())
                        .withSeating(request.getSeating())
                        .withExtraInformation(request.getExtraInformation())
                        .withRate(request.getRate())
                        .withRentAmountDay(request.getRentAmountDay())
                        .withImagePath(request.getImagePath())
                        .withCategory(request.getCategory())
                        .withMechanicConditions(request.getMechanicConditions()))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, carId));
    }

    @Override
    public ResponseEntity<?> delete(Long carId) {
        return carRepository.findById(carId).map(car -> {
                    carRepository.delete(car);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, carId));
    }
}
