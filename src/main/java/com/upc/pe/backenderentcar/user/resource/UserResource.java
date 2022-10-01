package com.upc.pe.backenderentcar.user.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;
    private String name;
    private String lastName;
    private String typeOfUser;
    private String imageUrl;
    private String email;
    private String password;
    private int phone;
}
