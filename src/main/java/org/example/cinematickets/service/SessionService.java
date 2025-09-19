package org.example.cinematickets.service;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.dto.MovieDto;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.dto.request.CreateSessionDto;
import org.example.cinematickets.model.Session;
import org.example.cinematickets.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;
    private final MovieService movieService;
    private final HallService hallService;

    public SessionDto createSession(Long movieId, Long hallId, LocalDateTime startTime, int price) {

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start time cannot be in the past");
        }

        var movie = MovieDto.toEntity(movieService.getMovieById(movieId));
        var hall = HallDto.toEntity(hallService.getHallById(hallId));

        var session = Session.builder()
                .movie(movie)
                .hall(hall)
                .startTime(startTime)
                .endTime(startTime.plusMinutes(movie.getDuration()))
                .price(price)
                .build();

        Session savedSession = sessionRepository.save(session);
        return SessionDto.fromEntity(savedSession);
    }

    public List<SessionDto> getAllSessions() {
        var sessions = sessionRepository.findAll();
        return sessions.stream()
                .map(SessionDto::fromEntity)
                .toList();
    }

    public SessionDto getSessionById(Long id) {
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session with id " + id + " not found"));
        return SessionDto.fromEntity(session);
    }

    public List<SessionDto> getSessionByHallId(Long hallId) {
        var sessions = sessionRepository.findByHallId(hallId);
        if (sessions.isEmpty()) {
            throw new IllegalArgumentException("No sessions found for hall id " + hallId);
        }
        return sessions.stream()
                .map(SessionDto::fromEntity)
                .toList();
    }

    public SessionDto getSessionByHallName(String name) {
        var session = sessionRepository.findByHallName(name);
        if (session == null) {
            throw new IllegalArgumentException("Session with hall name " + name + " not found");
        }
        return SessionDto.fromEntity(session);
    }

    public SessionDto getSessionByMovieTitle(String title) {
        var session = sessionRepository.findByMovieTitle(title);
        if (session == null) {
            throw new IllegalArgumentException("Session with movie title " + title + " not found");
        }
        return SessionDto.fromEntity(session);
    }

    public List<SessionDto> getSessionsByMovieId(Long movieId) {
        var sessions = sessionRepository.findByMovieId(movieId);
        if (sessions.isEmpty()) {
            throw new IllegalArgumentException("No sessions found for movie id " + movieId);
        }
        return sessions.stream()
                .map(SessionDto::fromEntity)
                .toList();
    }

    public SessionDto updateSession(Long id, CreateSessionDto createSessionDto) {
        var session = sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session with id " + id + " not found"));

        var movie = MovieDto.toEntity(movieService.getMovieById(createSessionDto.movieId()));
        var hall = HallDto.toEntity(hallService.getHallById(createSessionDto.hallId()));

        session.setMovie(movie);
        session.setHall(hall);
        session.setStartTime(createSessionDto.startTime());
        session.setEndTime(createSessionDto.startTime().plusMinutes(movie.getDuration()));
        session.setPrice(createSessionDto.price());

        Session updatedSession = sessionRepository.save(session);
        return SessionDto.fromEntity(updatedSession);
    }

    public void deleteSession(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new IllegalArgumentException("Session with id " + id + " not found");
        }
        sessionRepository.deleteById(id);
    }
}
