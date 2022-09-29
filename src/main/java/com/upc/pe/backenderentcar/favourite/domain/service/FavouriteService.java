package com.upc.pe.backenderentcar.favourite.domain.service;

import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavouriteService {
    List<Favourite> getAll();
    Page<Favourite> getAll(Pageable pageable);
    Page<Favourite> getAllFavouritesByUserId(Long userId, Pageable pageable);
    Favourite getById(Long favouriteId);
    Favourite create(Long userId, Long carId, Favourite request);
    ResponseEntity<?> delete(Long favouriteId);
}
