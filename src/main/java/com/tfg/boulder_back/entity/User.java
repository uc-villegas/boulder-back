package com.tfg.boulder_back.entity;

import com.tfg.boulder_back.constants.TypeUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "app_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name; // TODO: pattern Revisar campos ""

    @Column(name = "surname", nullable = true, length = 50)
    private String surname; // TODO: pattern

    @Column(name = "mail", nullable = false, unique = true, length = 50)
    private String email; // TODO: patternnn

    @Column(name = "password", nullable = false, length = 50)
    private String password; // TODO: pattern

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private TypeUser role;

    @Column(name = "boulder", nullable = true)
    private Long boulder;

}
