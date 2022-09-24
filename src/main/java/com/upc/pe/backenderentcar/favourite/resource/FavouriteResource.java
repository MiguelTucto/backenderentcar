package com.upc.pe.backenderentcar.favourite.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class FavouriteResource {
    private Long id;
    private Long carId;
    private Long userId;
}
