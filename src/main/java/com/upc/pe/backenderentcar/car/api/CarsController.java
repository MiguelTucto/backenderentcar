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

    @Operation(summary = "Get Cars", description = "You can get all cars", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get all cars",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping
    public Page<CarResource> getAllCars(Pageable pageable) {
        return mapper.modelListPage(carService.getAll(), pageable);
    }

    @Operation(summary = "Get Car by User", description = "You can get a car by an user id", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get a cara by an user id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping("{carId}")
    public CarResource getCarById (@PathVariable Long carId) {
        return mapper.toResource(carService.getById(carId));
    }

    @Operation(summary = "Get Cars by User", description = "You can get cars by an user id", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get cars by an user id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping("user/{userId}")
    public Page<CarResource> getAllCarsByUserId (@PathVariable Long userId, Pageable pageable) {
        return carService.getAllCarsByUserId(userId, pageable).map(mapper::toResource);
    }

    @Operation(summary = "Create Car", description = "You can create a car", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can create a car",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PostMapping("user/{userId}/")
    public CarResource createCar (@Valid @RequestBody CreateCarResource resource, @Valid @PathVariable Long userId) {
        return mapper.toResource(carService.create(mapper.toModel(resource), userId));
    }

    @Operation(summary = "Update Car", description = "You can update a car", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can update a car",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PutMapping("{carId}")
    public CarResource updateCar(@PathVariable Long carId, @Valid @RequestBody UpdateCarResource resource) {
        return mapper.toResource(carService.update(carId, mapper.toModel(resource)));
    }

    @Operation(summary = "Delete Car", description = "You can delete a car", tags = {"Cars"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can delete a car",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @DeleteMapping("{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) { return carService.delete(carId); }
}
