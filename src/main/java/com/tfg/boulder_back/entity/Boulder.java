package com.tfg.boulder_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
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
    private Long idBoulder;

    @Column(name = "name", nullable = false, length = 30)
    private String name; // TODO: pattern

    @Column(name = "address", nullable = true, length = 30)
    private String address; // TODO: pattern

    @Column(name = "locality", nullable = false, length = 30)
    private String locality;

    @Column(name = "mail", nullable = false, length = 30)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    private String mail; // TODO: pattern

    @Column(name = "phone", nullable = false, length = 30)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$")
    private String phone; // TODO: pattern

    @Column(name = "phone2", nullable = true, length = 30)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$")
    private String phone2; // TODO: pattern

    @OneToMany(mappedBy = "boulder", cascade = CascadeType.ALL)
    private Set<Route> routes;

}
