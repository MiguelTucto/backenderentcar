package com.upc.pe.backenderentcar.favourite.api;

import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import com.upc.pe.backenderentcar.favourite.domain.service.FavouriteService;
import com.upc.pe.backenderentcar.favourite.mapping.FavouriteMapper;
import com.upc.pe.backenderentcar.favourite.resource.CreateFavouriteResource;
import com.upc.pe.backenderentcar.favourite.resource.FavouriteResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Favourite")
@RestController
@CrossOrigin
@RequestMapping("/api/v1/favourites")
public class FavouritesController {
    private final FavouriteService favouriteService;
    private final FavouriteMapper mapper;

    public FavouritesController(FavouriteService favouriteService, FavouriteMapper mapper) {
        this.favouriteService = favouriteService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<FavouriteResource> getAllFavourites(Pageable pageable) {
        return mapper.modelListPage(favouriteService.getAll(), pageable);
    }

    @GetMapping("{favouriteId}")
    public FavouriteResource getFavouriteById (@PathVariable Long favouriteId) {
        return mapper.toResource(favouriteService.getById(favouriteId));
    }

    @GetMapping("user/{userId}")
    public Page<FavouriteResource> getAllFavouritesByUserId (@PathVariable Long userId, Pageable pageable) {
        return favouriteService.getAllFavouritesByUserId(userId,pageable).map(mapper::toResource);
    }

    @PostMapping("user/{userId}/car/{carId}")
    public FavouriteResource createFavourite(@PathVariable Long userId, @PathVariable Long carId, @Valid @RequestBody CreateFavouriteResource request) {
        return mapper.toResource(favouriteService.create(userId, carId, mapper.toModel(request)));
    }

    @DeleteMapping("{favouriteId}")
    public ResponseEntity<?> deleteFavourite (@PathVariable Long favouriteId) {
        return favouriteService.delete(favouriteId);
    }
}
