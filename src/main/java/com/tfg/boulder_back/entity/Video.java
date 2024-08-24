package com.tfg.boulder_back.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

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

    @Column(name = "url", nullable = false, unique = true, length = 100)
    private String url;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "publication_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_route", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Route route;

}
