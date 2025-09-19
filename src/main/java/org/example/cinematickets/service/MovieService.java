package org.example.cinematickets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.MovieDto;
import org.example.cinematickets.model.Movie;
import org.example.cinematickets.repository.MovieRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieService{
    private final MovieRepository movieRepository;

    @Transactional
    public MovieDto addMovie(String title,
                             String description,
                             int duration,
                             String genre,
                             String director) {

        var movie = Movie.builder()
                .title(title)
                .description(description)
                .duration(duration)
                .genre(genre)
                .director(director)
                .build();

        var savedMovie = movieRepository.save(movie);
        return MovieDto.fromEntity(savedMovie);
    }


    public MovieDto getMovieById(Long id) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie with id " + id + " not found"));
        return MovieDto.fromEntity(movie);
    }


    public MovieDto getMovieByTitle(String title) {
        var movie = movieRepository.findByTitle(title);
        if (movie == null) {
            throw new IllegalArgumentException("Movie with title " + title + " not found");
        }
        return MovieDto.fromEntity(movie);
    }


    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException("Movie with id " + id + " not found");
        }
        movieRepository.deleteById(id);
    }


    public MovieDto updateMovie(Long id,
                             String title,
                             String description,
                             int duration,
                             String genre,
                             String director) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie with id " + id + " not found"));

        if (title != null && !title.trim().isEmpty()) {
            movie.setTitle(title);
        }
        if (description != null && !description.trim().isEmpty()) {
            movie.setDescription(description);
        }
        if (duration > 0) {
            movie.setDuration(duration);
        }
        if (genre != null && !genre.trim().isEmpty()) {
            movie.setGenre(genre);
        }
        if (director != null && !director.trim().isEmpty()) {
            movie.setDirector(director);
        }

        var updatedMovie = movieRepository.save(movie);
        return MovieDto.fromEntity(updatedMovie);
    }
}
