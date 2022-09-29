package com.upc.pe.backenderentcar.rent.domain.persistence;

import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    Page<Rent> getRentByUserId(Long userId, Pageable pageable);
}
