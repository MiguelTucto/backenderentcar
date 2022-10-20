package com.upc.pe.backenderentcar.favourite.service;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.favourite.domain.model.entity.Favourite;
import com.upc.pe.backenderentcar.favourite.domain.persistence.FavouriteRepository;
import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import com.upc.pe.backenderentcar.user.domain.persistence.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FavouriteServiceImplTest {

    @Mock
    FavouriteRepository favouriteRepository;
    UserRepository userRepository;
     @InjectMocks
     FavouriteServiceImpl favouriteService;
     private Favourite favourite;
     private User user;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        favourite = new Favourite();
        Car car = new Car();
        User user = new User();
        favourite.setUser(user);
        favourite.setCar(car);
        favourite.setId(1L);

    }
    @Test
    void getAll() {
        when(favouriteRepository.findAll()).thenReturn(Arrays.asList(favourite));
        assertNotNull(favouriteRepository.findAll());
    }




    @Test
    void create() {
        when(favouriteRepository.save(favourite)).thenReturn(favourite);
        assertNotNull(favouriteRepository.findAll());
    }

    @Test
    void delete() {
        when(favouriteRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(favourite)).thenReturn(null);
    }
}