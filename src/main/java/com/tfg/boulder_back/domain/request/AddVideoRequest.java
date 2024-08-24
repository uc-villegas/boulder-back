package com.tfg.boulder_back.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddVideoRequest {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String url;

    @NotNull
    private int duration;

    private Date publicationDate;

}
