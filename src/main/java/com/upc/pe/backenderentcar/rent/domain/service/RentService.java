package com.upc.pe.backenderentcar.rent.domain.service;

import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentService {
    List<Rent> getAll();
    Page<Rent> getAll(Pageable pageable);
    Rent getById(Long rentId);
    Rent create(Long userId, Long carId, Rent request);
    Rent update(Long rentId, Rent request);
    ResponseEntity<?> delete(Long rentId);
}
