package com.upc.pe.backenderentcar.car.api;

import com.upc.pe.backenderentcar.car.domain.service.CarService;
import com.upc.pe.backenderentcar.car.mapping.CarMapper;
import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.car.resource.CreateCarResource;
import com.upc.pe.backenderentcar.car.resource.UpdateCarResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

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

    @GetMapping("user/{userId}")
    public Page<CarResource> getAllCarsByUserId (@PathVariable Long userId, Pageable pageable) {
        return carService.getAllCarsByUserId(userId, pageable).map(mapper::toResource);
    }

    @Operation(summary = "Create Car", description = "Create Car", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PostMapping("user/{userId}/")
    public CarResource createCar (@Valid @RequestBody CreateCarResource resource, @Valid @PathVariable Long userId) {
        return mapper.toResource(carService.create(mapper.toModel(resource), userId));
    }

    @PutMapping("{carId}")
    public CarResource updateCar(@PathVariable Long carId, @Valid @RequestBody UpdateCarResource resource) {
        return mapper.toResource(carService.update(carId, mapper.toModel(resource)));
    }

    @DeleteMapping("{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) { return carService.delete(carId); }
}
