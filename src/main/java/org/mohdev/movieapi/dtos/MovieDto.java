package org.mohdev.movieapi.dtos;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Integer id;

    @NotBlank(message = "Please provide movie's title")
    private String title;


    @NotBlank(message = "Please provide movie's director")
    private String director;


    @NotBlank(message = "Please provide movie's studio")
    private String studio;

    private Set<String> movieCast;

    @NotBlank(message = "Please provide release movie's release year")
    private Integer releaseYear;

    @NotBlank(message = "Please provide movie's poster")
    private String poster;

    @NotBlank(message = "Please provide movie's poster url")
    private String posterUrl;
}
