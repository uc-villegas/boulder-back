package com.tfg.boulder_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Video", schema = "boulder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_video", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "url", nullable = false, length = 100)
    private String url;

    @Column(name = "duration", nullable = false)
    private int duration;

    // TODO: Fecha de publicacion en la app. Para poder ordenar por mas recientes. Quiero mostrarlo en cada video de la lista, en detalles via.

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_route", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Route route;

}
