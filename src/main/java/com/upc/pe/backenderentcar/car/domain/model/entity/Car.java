package com.upc.pe.backenderentcar.car.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String brand;

    private int year;

    @NotNull
    @NotBlank
    private String model;


    private int mileage;


    private int seating;

    @NotNull
    private boolean manual;


    private int carValueInDollars;

    @NotNull
    @NotBlank
    private String extraInformation;


    private int rate;


    private int rentAmountDay;

    @NotNull
    @NotBlank
    private String imagePath;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String mechanicConditions;
}
