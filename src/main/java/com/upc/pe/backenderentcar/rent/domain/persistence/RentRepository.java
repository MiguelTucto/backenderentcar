package com.upc.pe.backenderentcar.rent.domain.persistence;

import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
