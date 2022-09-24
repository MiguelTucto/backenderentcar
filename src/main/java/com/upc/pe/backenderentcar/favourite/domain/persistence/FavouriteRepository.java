package com.upc.pe.backenderentcar.favourite.domain.persistence;

import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Page<Favourite> findByUserId(Long clientId, Pageable pageable);
}
