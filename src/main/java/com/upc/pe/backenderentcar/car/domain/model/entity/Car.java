package com.upc.pe.backenderentcar.car.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String brand;

    private int year;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String model;

    private int mileage;

    private int seating;

    private boolean manual;

    private int carValueInDollars;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String extraInformation;

    private int rate;

    private int rentAmountDay;

    @NotNull
    @NotBlank
    @Size(max = 200)
    @Column(unique = true)
    private String imagePath;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String category;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String mechanicConditions;
}
