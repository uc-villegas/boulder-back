package com.tfg.boulder_back.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Route", schema = "boulder")
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long idRoute;

    @Column(name = "name", nullable = false, length = 10)
    private String name; // TODO: pattern

    @Column(name = "difficulty", nullable = false, length = 10)
    private String difficulty; // TODO: enum

    @Column(name = "color", nullable = false, length = 20)
    private String color; // TODO: enum

    @Column(name = "creationDate", nullable = false, length = 20)
    private Date creationDate;

    @OneToMany(mappedBy = "route")
    private Set<Video> videos;

    @ManyToOne
    @JoinColumn(name = "id_boulder")
    private Boulder boulder;
}
