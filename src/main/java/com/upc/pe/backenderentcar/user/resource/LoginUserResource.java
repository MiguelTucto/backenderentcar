package com.upc.pe.backenderentcar.user.resource;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResource {
    @Size(max = 20)
    private String email;

    @Size(max = 10)
    private String password;
}
