package org.example.cinematickets.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.dto.MovieDto;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.dto.request.CreateSessionDto;
import org.example.cinematickets.dto.request.HallCreationDto;
import org.example.cinematickets.dto.request.MovieCreationDto;
import org.example.cinematickets.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/halls")
    public ResponseEntity<HallDto> createHall(@Valid @RequestBody HallCreationDto hallCreationDto) {
        var hallDto =  adminService.createHall(hallCreationDto);
        return ResponseEntity.ok(hallDto);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> addMovie(@Valid @RequestBody MovieCreationDto movieCreationDto) {
        var movieDto = adminService.addMovie(movieCreationDto);
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/sessions")
    public ResponseEntity<SessionDto> createSession(@Valid @RequestBody CreateSessionDto createSessionDto) {
        var sessionDto = adminService.createSession(createSessionDto);
        return ResponseEntity.ok(sessionDto);
    }
}
