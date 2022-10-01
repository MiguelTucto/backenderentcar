package com.upc.pe.backenderentcar.user.domain.model.entity;

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
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Size(max = 50)
    private String typeOfUser;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 200)
    @Column(unique = true)
    private String imageUrl;


    private int phone;
}
