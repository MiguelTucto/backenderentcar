package com.upc.pe.backenderentcar.car.resource;

import com.upc.pe.backenderentcar.user.resource.UserResource;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CarResource {
    private Long id;
    private String address;
    private String brand;
    private int year;
    private String model;
    private int mileage;
    private int seating;
    private boolean manual;
    private int carValueInDollars;
    private String extraInformation;
    private int rate;
    private int rentAmountDay;
    private String imagePath;
    private String category;
    private String mechanicConditions;
    private Long userId;
}
