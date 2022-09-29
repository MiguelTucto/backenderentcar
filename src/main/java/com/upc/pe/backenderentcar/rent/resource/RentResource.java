package com.upc.pe.backenderentcar.rent.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class RentResource {
    private Long id;
    private String startDate;
    private String finishDate;
    private int amount;
    private int rate;
    private Long userId;
    private Long carId;
}
