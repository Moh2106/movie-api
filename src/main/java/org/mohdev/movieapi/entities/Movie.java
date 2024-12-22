package org.mohdev.movieapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Please provide movie's title")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Please provide director's title")
    private String director;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Please provide studio's title")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false)
    private Integer releaseYear;


}
