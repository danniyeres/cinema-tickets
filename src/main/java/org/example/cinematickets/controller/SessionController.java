package org.example.cinematickets.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.service.SessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    private final SessionService sessionService;

    @GetMapping("/all")
    public List<SessionDto> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public SessionDto getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    @GetMapping("/hall/{hallId}")
    public List<SessionDto> getSessionByHallId(@PathVariable Long hallId) {
        return sessionService.getSessionByHallId(hallId);
    }

    @GetMapping("/movie/{movieId}")
    public List<SessionDto> getSessionByMovieId(@PathVariable Long movieId) {
        return sessionService.getSessionsByMovieId(movieId);
    }

}
