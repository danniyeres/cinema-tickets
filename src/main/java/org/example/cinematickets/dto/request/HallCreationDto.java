package org.example.cinematickets.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record HallCreationDto(

        @NotNull(message = "Hall name cannot be null")
        String name,

        @NotNull(message = "Number of rows cannot be null")
        @Positive(message = "Number of rows must be positive")
        int rows,

        @NotNull(message = "Number of seats per row cannot be null")
        @Positive(message = "Number of seats per row must be positive")
        int seatsPerRow) {
}