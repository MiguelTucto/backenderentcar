package com.upc.pe.backenderentcar.user.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateUserResource {
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 10)
    @Column(unique = true)
    private String imageUrl;

    private int phone;
}
