package com.tfg.boulder_back.entity;


import com.tfg.boulder_back.constants.TypeRoute;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "Route")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route", nullable = false, length = 5)
    private Long id;

    @Column(name = "qr", nullable = false, length = 50)
    private String qrRoute;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name; // TODO: pattern

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeRoute typeRoute;

    @Column(name = "num_nivel", nullable = false, length = 50)
    private int num_nivel; // numerico

    @Column(name = "presa", nullable = false, length = 50)
    private String presa; // Colores

    @Column(name = "creationDate", nullable = false, length = 50, updatable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_boulder", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Boulder boulder;
}
