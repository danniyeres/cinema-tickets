package org.example.cinematickets.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.service.HallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/halls")
@RequiredArgsConstructor
public class HallController {
    private final HallService hallService;

    @GetMapping("/{id}")
    public HallDto getHallById(@PathVariable Long id) {
        return hallService.getHallById(id);
    }
}
