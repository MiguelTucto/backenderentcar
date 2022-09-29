package com.upc.pe.backenderentcar.rent.api;

import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.rent.domain.service.RentService;
import com.upc.pe.backenderentcar.rent.mapping.RentMapper;
import com.upc.pe.backenderentcar.rent.resource.CreateRentResource;
import com.upc.pe.backenderentcar.rent.resource.RentResource;
import com.upc.pe.backenderentcar.rent.resource.UpdateRentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rents")
@CrossOrigin
public class RentsController {
    private final RentService rentService;
    private final RentMapper mapper;

    public RentsController(RentService rentService, RentMapper mapper) {
        this.rentService = rentService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get All Rents", description = "You can get all rents", tags = {"Rents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get all rents",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping
    public Page<RentResource> getAllRents(Pageable pageable) {
        return mapper.modelListToPage(rentService.getAll(), pageable);
    }

    @Operation(summary = "Get Rent", description = "You can get a rent by id", tags = {"Rents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get a rent by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping({"rentId"})
    public RentResource getRentById(@PathVariable Long rentId) {
        return mapper.toResource(rentService.getById(rentId));
    }

    @Operation(summary = "Create Rent by User and Car", description = "You can create a rent by car and user id", tags = {"Rents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can create a rent by car and user id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PostMapping("user/{userId}/car/{carId}")
    public RentResource createRent (@PathVariable Long userId, @PathVariable Long carId, @Valid @RequestBody CreateRentResource request) {
        return mapper.toResource(rentService.create(userId, carId, mapper.toModel(request)));
    }

    @Operation(summary = "Update Rent", description = "You can update a rent by id", tags = {"Rents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can update a rent by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PutMapping("{rentId}")
    public RentResource updateRent (@PathVariable Long rentId, @Valid @RequestBody UpdateRentResource request) {
        return mapper.toResource(rentService.update(rentId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Rent", description = "You can delete a rent by id", tags = {"Rents"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can delete a rent by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @DeleteMapping("{rentId}")
    public ResponseEntity<?> deleteRent (@PathVariable Long rentId) {
        return rentService.delete(rentId);
    }
}
