package com.tfg.boulder_back.domain.request;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVideoRequest {

    private String title;
    private String description;

}
