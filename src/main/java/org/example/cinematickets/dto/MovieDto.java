package org.example.cinematickets.dto;

import org.example.cinematickets.model.Movie;

public record MovieDto(
        Long id,
        String title,
        String description,
        int duration,
        String genre,
        String director) {

    public static MovieDto fromEntity(org.example.cinematickets.model.Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getDirector()
        );
    }

    public static Movie toEntity(MovieDto movieDto) {
        return Movie.builder()
                .id(movieDto.id())
                .title(movieDto.title())
                .description(movieDto.description())
                .duration(movieDto.duration())
                .genre(movieDto.genre())
                .director(movieDto.director())
                .build();
    }
}