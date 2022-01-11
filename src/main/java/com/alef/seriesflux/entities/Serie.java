package com.alef.seriesflux.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
@Document
public class Serie {

    @Id
    private String id;
    private String title;
    private String genres;
    private String datee;
}
