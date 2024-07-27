package com.tfg.boulder_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Boulder", schema = "boulder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boulder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boulder", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long idBoulder;

    @Column(name = "name", nullable = false, length = 10)
    private String name; // TODO: pattern

    @Column(name = "address", nullable = true, length = 10)
    private String address; // TODO: pattern

    @Column(name = "locality", nullable = false, length = 20)
    private String locality;

    @Column(name = "mail", nullable = false, length = 20)
    private String mail; // TODO: pattern

    @Column(name = "phone", nullable = false, length = 20)
    private String phone; // TODO: pattern

    @Column(name = "phone2", nullable = true, length = 20)
    private String phone2; // TODO: pattern

    @OneToMany(mappedBy = "boulder")
    private Set<Route> rutas;

}
