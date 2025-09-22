package org.example.cinematickets.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.dto.MovieDto;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.dto.request.SessionRequest;
import org.example.cinematickets.dto.request.HallRequest;
import org.example.cinematickets.dto.request.MovieRequest;
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
    public ResponseEntity<HallDto> createHall(@Valid @RequestBody HallRequest hallRequest) {
        var hallDto =  adminService.createHall(hallRequest);
        return ResponseEntity.ok(hallDto);
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieDto> addMovie(@Valid @RequestBody MovieRequest movieRequest) {
        var movieDto = adminService.addMovie(movieRequest);
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/sessions")
    public ResponseEntity<SessionDto> createSession(@Valid @RequestBody SessionRequest sessionRequest) {
        var sessionDto = adminService.createSession(sessionRequest);
        return ResponseEntity.ok(sessionDto);
    }
}
