package com.upc.pe.backenderentcar.rent.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upc.pe.backenderentcar.car.domain.model.entity.Car;
import com.upc.pe.backenderentcar.user.domain.model.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "rents")
public class Rent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String startDate;

    @NotNull
    @NotBlank
    @Size(max = 15)
    private String finishDate;

    @NotNull
    private int amount;

    @NotNull
    private int rate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @JsonIgnore
    private Car car;
}
