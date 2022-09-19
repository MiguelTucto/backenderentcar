package com.upc.pe.backenderentcar.car.api;

import com.upc.pe.backenderentcar.car.domain.service.CarService;
import com.upc.pe.backenderentcar.car.mapping.CarMapper;
import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.car.resource.CreateCarResource;
import com.upc.pe.backenderentcar.car.resource.UpdateCarResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@Tag(name = "Car")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/cars")
public class CarsController {
    private final CarService carService;
    private final CarMapper mapper;

    public CarsController(CarService carService, CarMapper mapper) {
        this.carService = carService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<CarResource> getAllCars(Pageable pageable) {
        return mapper.modelListPage(carService.getAll(), pageable);
    }

    @GetMapping("{carId}")
    public CarResource getCarById (@PathVariable Long carId) {
        return mapper.toResource(carService.getById(carId));
    }

    @PostMapping
    public CarResource createCar (@RequestBody CreateCarResource resource) {
        return mapper.toResource(carService.create(mapper.toModel(resource)));
    }

    @PutMapping("{carId}")
    public CarResource updateCar(@PathVariable Long carId, @RequestBody UpdateCarResource resource) {
        return mapper.toResource(carService.update(carId, mapper.toModel(resource)));
    }

    @DeleteMapping("{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) { return carService.delete(carId); }
}
