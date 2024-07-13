package com.tfg.boulder_back.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "Route", schema = "boulder")
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoute", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long idRoute;

    @Column(name = "name", nullable = false, length = 10)
    private String name; // TODO: pattern

    @Column(name = "difficulty", nullable = false, length = 10)
    private String difficulty; // TODO: enum

    @Column(name = "color", nullable = false, length = 20)
    private String color; // TODO: enum

    //TODO: Fecha de creacion (Para filtros)

    //TODO: @ManyToOne Rocodromo
}
