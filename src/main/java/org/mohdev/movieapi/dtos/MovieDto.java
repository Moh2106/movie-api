package org.mohdev.movieapi.dtos;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
public class MovieDto {
    private Integer id;

    @NotBlank(message = "Please provide movie's title")
    private String title;


    @NotBlank(message = "Please provide movie's director")
    private String director;


    @NotBlank(message = "Please provide movie's studio")
    private String studio;

    private Set<String> movieCast;


    private Integer releaseYear;


}
