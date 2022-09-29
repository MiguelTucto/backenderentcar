package com.upc.pe.backenderentcar.car.resource;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarResource {
    @NotNull
    @NotBlank
    @Size(max = 10)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String brand;

    private int year;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String model;

    private int mileage;

    private int seating;

    private String manual;

    private int carValueInDollars;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String extraInformation;

    private int rate;

    private int rentAmountDay;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String imagePath;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String category;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String mechanicConditions;
}
