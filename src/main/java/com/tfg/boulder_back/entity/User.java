package com.tfg.boulder_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Table(name = "User", schema = "boulder")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long id;

    @Column(name = "name", nullable = false, length = 10)
    private String name; // TODO: pattern

    @Column(name = "surname", nullable = true, length = 10)
    private String surname; // TODO: pattern

    @Column(name = "mail", nullable = false, length = 20)
    private String mail; // TODO: pattern

    // TODO: ¿Videos?¿Galeria?

}
