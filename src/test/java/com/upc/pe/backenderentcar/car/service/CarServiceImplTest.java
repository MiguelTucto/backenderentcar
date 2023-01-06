package com.upc.pe.backenderentcar.car.service;

import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.car.domain.persistence.CarRepository;
import com.upc.pe.backenderentcar.car.resource.UpdateCarResource;
import com.upc.pe.backenderentcar.rent.domain.model.entity.Rent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarServiceImplTest {
    @Mock
    CarRepository carRepository;
    @InjectMocks
    CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp(){
    MockitoAnnotations.openMocks(this);
        Car car = new Car();
        car.setAddress("La Molina");
        car.setBrand("Coachella");
        car.setCategory("deportivo");
        car.setCarValueInDollars(45000);
        car.setImagePath("https://www.google.com/url?sa=i&url=https%3A%2F%2Frpp.pe%2Fmusica%2Fconciertos%2Fdaddy-yankee-en-lima-fecha-de-preventa-de-entradas-para-su-concierto-en-peru-noticia-1394135&psig=AOvVaw2X8jQJfartT2Zt454C3KM_&ust=1666378284529000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCJCsjoC97_oCFQAAAAAdAAAAABAI");
        car.setExtraInformation("rojo");
        car.setManual("manual");
        car.setMechanicConditions("mecanic");
        car.setMileage(3);
        car.setModel("mercedes");
        car.setRentAmountDay(500);
        car.setSeating(200);
        car.setYear(2020);
        car.setId(1L);
    }
    @Test
    void getAll() {

        when(carRepository.findAll()).thenReturn(Arrays.asList(car));
        assertNotNull(carRepository.findAll());
    }


    @Test
    void getById() {
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(car));
        assertNotNull(carRepository.findById(1L));
    }

    @Test
    void create() {
        when(carRepository.save(car)).thenReturn(car);
        assertNotNull(carRepository.findAll());
    }

    @Test
    void update() {
        Car car2 = new Car();
        car2.setAddress("La Molina");
        car2.setBrand("Coachella");
        car2.setCategory("deportivo");
        car2.setCarValueInDollars(45000);
        car2.setImagePath("https://www.google.com/url?sa=i&url=https%3A%2F%2Frpp.pe%2Fmusica%2Fconciertos%2Fdaddy-yankee-en-lima-fecha-de-preventa-de-entradas-para-su-concierto-en-peru-noticia-1394135&psig=AOvVaw2X8jQJfartT2Zt454C3KM_&ust=1666378284529000&source=images&cd=vfe&ved=0CA0QjRxqFwoTCJCsjoC97_oCFQAAAAAdAAAAABAI");
        car2.setExtraInformation("rojo");
        car2.setManual("manual");
        car2.setMechanicConditions("mecanic");
        car2.setMileage(3);
        car2.setModel("mercedes");
        car2.setRentAmountDay(500);
        car2.setSeating(400);
        car2.setYear(2020);
        car2.setId(1L);
        when(carRepository.findById(car2.getId())).thenReturn(java.util.Optional.of(car2));

        ArgumentCaptor<Car> carArgument = ArgumentCaptor.forClass(Car.class);

        when(carRepository.saveAndFlush(carArgument.capture())).thenAnswer(iom->iom.getArgument(0));


        assertNotNull(carRepository.findById(car2.getId()));
    }

    @Test
    void delete() {
        when(carRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(car)).thenReturn(null);
    }
}