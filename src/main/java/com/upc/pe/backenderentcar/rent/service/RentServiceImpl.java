package com.upc.pe.backenderentcar.rent.service;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.car.domain.persistence.CarRepository;
import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import com.upc.pe.backenderentcar.rent.domain.persistence.RentRepository;
import com.upc.pe.backenderentcar.rent.domain.service.RentService;
import com.upc.pe.backenderentcar.shared.exception.ResourceNotFoundException;
import com.upc.pe.backenderentcar.shared.exception.ResourceValidationException;
import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import com.upc.pe.backenderentcar.user.domain.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class RentServiceImpl implements RentService {
    private static final String ENTITY = "Rent";
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final Validator validator;

    public RentServiceImpl(RentRepository rentRepository, UserRepository userRepository, CarRepository carRepository, Validator validator) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.validator = validator;
    }


    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Page<Rent> getAll(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }

    @Override
    public Page<Rent> getAllRentsByUserId(Long userId, Pageable pageable) {
        return rentRepository.getRentByUserId(userId, pageable);
    }

    @Override
    public Rent getById(Long rentId) {
        return rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentId));
    }

    @Override
    public Rent create(Long userId, Long carId, Rent request) {
        Set<ConstraintViolation<Rent>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId));
        request.setUser(user);

        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car", carId));
        request.setCar(car);

        return rentRepository.save(request);
    }

    @Override
    public Rent update(Long rentId, Rent request) {
        Set<ConstraintViolation<Rent>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return rentRepository.findById(rentId).map(rent ->
                rentRepository.save(rent.withStartDate(request.getStartDate())
                        .withFinishDate(request.getFinishDate())
                        .withAmount(request.getAmount())
                        .withRate(request.getRate())
                )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentId));
    }

    @Override
    public ResponseEntity<?> delete(Long rentId) {
        return rentRepository.findById(rentId).map(rent -> {
            rentRepository.delete(rent);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, rentId));
    }
}
