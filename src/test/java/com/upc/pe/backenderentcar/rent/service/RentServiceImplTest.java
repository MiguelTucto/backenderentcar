package com.upc.pe.backenderentcar.rent.service;

import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import com.upc.pe.backenderentcar.rent.domain.persistence.RentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RentServiceImplTest {
    @Mock
    RentRepository rentRepository;
    @InjectMocks
    RentServiceImpl rentService;
    private Rent rent;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        Rent rent = new Rent();
        rent.setAmount(5);
        rent.setRate(2);
        rent.setFinishDate("19/09/2022");
        rent.setStartDate("02/09/2022");
        rent.setId(1L);
    }
    @Test
    void getAll() {
        when(rentRepository.findAll()).thenReturn(Arrays.asList(rent));
        assertNotNull(rentRepository.findAll());
    }

    @Test
    void getById() {
        when(rentRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(rent));
        assertNotNull(rentRepository.findById(1L));
    }

    @Test
    void create() {
        when(rentRepository.save(rent)).thenReturn(rent);
        assertNotNull(rentRepository.findAll());
    }

    @Test
    void delete() {
        when(rentRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(rent)).thenReturn(null);
    }
}