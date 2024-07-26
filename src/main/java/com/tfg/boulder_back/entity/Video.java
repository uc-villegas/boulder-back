package com.tfg.boulder_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "Video", schema = "boulder")
@Data
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route", nullable = false, length = 5)
    @Pattern(regexp = "\\d")
    private Long id;

    @Column(name = "title", nullable = false, length = 10)
    private String title;

    @Column(name = "description", nullable = false, length = 10)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "url", nullable = false, length = 10)
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_route")
    private Route route;
}
