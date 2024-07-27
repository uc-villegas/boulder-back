package com.tfg.boulder_back.entity;


import com.tfg.boulder_back.constants.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Route", schema = "boulder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long idRoute;

    @Column(name = "qr", nullable = false)
    private String qrRoute;

    @Column(name = "name", nullable = false, length = 10)
    private String name; // TODO: pattern

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "num_nivel", nullable = false, length = 10)
    private int num_nivel; // numerico

    @Column(name = "presa", nullable = false, length = 20)
    private String presa; // Colores

    @Column(name = "creationDate", nullable = false, length = 20)
    private Date creationDate;

    @OneToMany(mappedBy = "route")
    private Set<Video> videos;

    @ManyToOne
    @JoinColumn(name = "id_boulder")
    private Boulder boulder;
}
