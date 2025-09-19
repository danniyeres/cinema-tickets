package org.example.cinematickets.dto;

import org.example.cinematickets.model.Seat;

public record SeatDto(
        Long id,
        int rowNumber,
        int seatNumber) {

    public static SeatDto fromEntity(Seat seat) {
        return new SeatDto(
                seat.getId(),
                seat.getRowNumber(),
                seat.getSeatNumber()
        );
    }
}

