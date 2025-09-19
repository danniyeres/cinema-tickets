package org.example.cinematickets.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateSessionDto(

        @NotNull(message = "Movie ID cannot be null")
        @Positive(message = "Movie ID must be positive")
        Long movieId,

        @NotNull(message = "Hall ID cannot be null")
        @Positive(message = "Hall ID must be positive")
        Long hallId,

        @NotNull(message = "Start time cannot be null")
        LocalDateTime startTime,

        @Positive(message = "Price must be positive")
        int price
) {}
