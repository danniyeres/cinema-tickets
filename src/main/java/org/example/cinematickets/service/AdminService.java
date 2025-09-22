package org.example.cinematickets.service;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.dto.MovieDto;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.dto.request.SessionRequest;
import org.example.cinematickets.dto.request.HallRequest;
import org.example.cinematickets.dto.request.MovieRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final HallService hallService;
    private final MovieService movieService;
    private final SessionService sessionService;

    public HallDto createHall(HallRequest hallRequest) {
        return hallService.createHall(
                hallRequest.name(),
                hallRequest.rows(),
                hallRequest.seatsPerRow()
        );
    }

    public MovieDto addMovie(MovieRequest movieRequest) {
        return movieService.addMovie(
                movieRequest.title(),
                movieRequest.description(),
                movieRequest.duration(),
                movieRequest.genre(),
                movieRequest.director()
        );
    }

    public SessionDto createSession(SessionRequest sessionRequest) {
        return sessionService.createSession(
                sessionRequest.movieId(),
                sessionRequest.hallId(),
                sessionRequest.startTime(),
                sessionRequest.price()
        );
    }


}
