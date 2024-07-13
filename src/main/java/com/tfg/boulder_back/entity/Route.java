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
    @Column(name = "id", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long id;

    @Column(name = "name", nullable = false, length = 10)
    private String name; // TODO: pattern

    @Column(name = "difficulty", nullable = false, length = 10)
    private String difficulty; // TODO: enum

    @Column(name = "color", nullable = false, length = 20)
    private String color; // TODO: enum

    @Column(name = "phone", nullable = false, length = 20)
    private String phone; // TODO: pattern

    @Column(name = "phone2", nullable = true, length = 20)
    private String phone2; // TODO: pattern

    //TODO: @ManyToOne Rocodromo
}
