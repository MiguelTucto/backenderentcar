package com.upc.pe.backenderentcar.favourite.service;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.car.domain.persistence.CarRepository;
import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import com.upc.pe.backenderentcar.favourite.domain.persistence.FavouriteRepository;
import com.upc.pe.backenderentcar.favourite.domain.service.FavouriteService;
import com.upc.pe.backenderentcar.shared.exception.ResourceNotFoundException;
import com.upc.pe.backenderentcar.shared.exception.ResourceValidationException;
import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import com.upc.pe.backenderentcar.user.domain.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class FavouriteServiceImpl implements FavouriteService {
     private static final String ENTITY = "Favourite";
     private final FavouriteRepository favouriteRepository;
     private final UserRepository userRepository;
     private final CarRepository carRepository;
     private final Validator validator;

    public FavouriteServiceImpl(FavouriteRepository favouriteRepository, UserRepository userRepository, CarRepository carRepository, Validator validator) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.validator = validator;
    }


    @Override
    public List<Favourite> getAll() {
        return favouriteRepository.findAll();
    }

    @Override
    public Page<Favourite> getAll(Pageable pageable) {
        return favouriteRepository.findAll(pageable);
    }

    @Override
    public Page<Favourite> getAllFavouritesByUserId(Long userId, Pageable pageable) {
        return favouriteRepository.findByUserId(userId, pageable);
    }

    @Override
    public Favourite getById(Long favouriteId) {
        return favouriteRepository.findById(favouriteId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, favouriteId));
    }

    @Override
    public Favourite create(Long userId, Long carId, Favourite request) {
        Set<ConstraintViolation<Favourite>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
        request.setUser(user);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", carId));
        request.setCar(car);

        return favouriteRepository.save(request);
    }

    @Override
    public ResponseEntity<?> delete(Long favouriteId) {
        return favouriteRepository.findById(favouriteId).map(favourite -> {
            favouriteRepository.delete(favourite);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, favouriteId));
    }
}
