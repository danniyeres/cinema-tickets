package org.example.cinematickets.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovieCreationDto(

        @NotNull(message = "Title cannot be null")
        String title,

        @NotNull(message = "Description cannot be null")
        String description,

        @NotNull(message = "Duration cannot be null")
        @Positive(message = "Duration must be greater than 0")
        int duration,

        @NotNull(message = "Genre cannot be null")
        String genre,

        @NotNull(message = "Director cannot be null")
        String director) {
}