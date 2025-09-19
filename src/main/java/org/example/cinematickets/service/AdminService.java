package org.example.cinematickets.service;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.dto.MovieDto;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.dto.request.CreateSessionDto;
import org.example.cinematickets.dto.request.HallCreationDto;
import org.example.cinematickets.dto.request.MovieCreationDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final HallService hallService;
    private final MovieService movieService;
    private final SessionService sessionService;

    public HallDto createHall(HallCreationDto hallCreationDto) {
        return hallService.createHall(
                hallCreationDto.name(),
                hallCreationDto.rows(),
                hallCreationDto.seatsPerRow()
        );
    }

    public MovieDto addMovie(MovieCreationDto movieCreationDto) {
        return movieService.addMovie(
                movieCreationDto.title(),
                movieCreationDto.description(),
                movieCreationDto.duration(),
                movieCreationDto.genre(),
                movieCreationDto.director()
        );
    }

    public SessionDto createSession(CreateSessionDto createSessionDto) {
        return sessionService.createSession(
                createSessionDto.movieId(),
                createSessionDto.hallId(),
                createSessionDto.startTime(),
                createSessionDto.price()
        );
    }


}
