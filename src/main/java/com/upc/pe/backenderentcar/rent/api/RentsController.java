package com.upc.pe.backenderentcar.rent.api;

import com.upc.pe.backenderentcar.rent.domain.service.RentService;
import com.upc.pe.backenderentcar.rent.mapping.RentMapper;
import com.upc.pe.backenderentcar.rent.resource.CreateRentResource;
import com.upc.pe.backenderentcar.rent.resource.RentResource;
import com.upc.pe.backenderentcar.rent.resource.UpdateRentResource;
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

    @GetMapping
    public Page<RentResource> getAllRents(Pageable pageable) {
        return mapper.modelListToPage(rentService.getAll(), pageable);
    }

    @GetMapping({"rentId"})
    public RentResource getRentById(@PathVariable Long rentId) {
        return mapper.toResource(rentService.getById(rentId));
    }

    @PostMapping("user/{userId}/car/{carId}")
    public RentResource createRent (@PathVariable Long userId, @PathVariable Long carId, @Valid @RequestBody CreateRentResource request) {
        return mapper.toResource(rentService.create(userId, carId, mapper.toModel(request)));
    }

    @PutMapping("{rentId}")
    public RentResource updateRent (@PathVariable Long rentId, @Valid @RequestBody UpdateRentResource request) {
        return mapper.toResource(rentService.update(rentId, mapper.toModel(request)));
    }

    @DeleteMapping("{rentId}")
    public ResponseEntity<?> deleteRent (@PathVariable Long rentId) {
        return rentService.delete(rentId);
    }
}
