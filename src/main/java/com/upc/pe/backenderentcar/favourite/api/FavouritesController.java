package com.upc.pe.backenderentcar.favourite.api;

import com.upc.pe.backenderentcar.car.resource.CarResource;
import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import com.upc.pe.backenderentcar.favourite.domain.service.FavouriteService;
import com.upc.pe.backenderentcar.favourite.mapping.FavouriteMapper;
import com.upc.pe.backenderentcar.favourite.resource.CreateFavouriteResource;
import com.upc.pe.backenderentcar.favourite.resource.FavouriteResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @Operation(summary = "Get Favourites", description = "You can get all favourites", tags = {"Favourites"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get all favourites",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping
    public Page<FavouriteResource> getAllFavourites(Pageable pageable) {
        return mapper.modelListPage(favouriteService.getAll(), pageable);
    }

    @Operation(summary = "Get Favourites by User", description = "You can get a favourite by id", tags = {"Favourites"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get a favourite by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping("{favouriteId}")
    public FavouriteResource getFavouriteById (@PathVariable Long favouriteId) {
        return mapper.toResource(favouriteService.getById(favouriteId));
    }

    @Operation(summary = "Get All Favourites by User", description = "You can get all favourites by id", tags = {"Favourites"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can get all favourites by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @GetMapping("user/{userId}")
    public Page<FavouriteResource> getAllFavouritesByUserId (@PathVariable Long userId, Pageable pageable) {
        return favouriteService.getAllFavouritesByUserId(userId,pageable).map(mapper::toResource);
    }

    @Operation(summary = "Create Favourite by User and Car", description = "You can create a favourite by user and car id", tags = {"Favourites"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can create a favourite by user and car id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @PostMapping("user/{userId}/car/{carId}")
    public FavouriteResource createFavourite(@PathVariable Long userId, @PathVariable Long carId, @Valid @RequestBody CreateFavouriteResource request) {
        return mapper.toResource(favouriteService.create(userId, carId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete Favourite", description = "You can delete a favourite by id", tags = {"Favourites"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "You can delete a favourite by id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarResource.class)
                    ))
    })
    @DeleteMapping("{favouriteId}")
    public ResponseEntity<?> deleteFavourite (@PathVariable Long favouriteId) {
        return favouriteService.delete(favouriteId);
    }
}
