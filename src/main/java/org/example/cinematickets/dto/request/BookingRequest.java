package org.example.cinematickets.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BookingRequest(

        @NotNull(message = "Session ID cannot be null")
        @Positive(message = "Session ID must be positive")
        Long sessionId,

        @NotNull(message = "Seat ID cannot be null")
        @Positive(message = "Seat ID must be positive")
        Long seatId,

        @NotNull(message = "Customer name cannot be null")
        String customerName,

        @NotNull(message = "Customer email cannot be null")
        String customerEmail){

}
