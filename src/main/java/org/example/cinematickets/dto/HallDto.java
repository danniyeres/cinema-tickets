package org.example.cinematickets.dto;

import org.example.cinematickets.model.Hall;

import java.util.List;

public record HallDto(
        Long id,
        String name,
        int rows,
        int seatsPerRow,
        List<SeatDto> seats) {

    public static HallDto fromEntity(Hall hall) {
        return new HallDto(
                hall.getId(),
                hall.getName(),
                hall.getRows(),
                hall.getSeatsPerRow(),
                hall.getSeats().stream()
                        .map(SeatDto::fromEntity)
                        .toList()
        );
    }

    public static Hall toEntity(HallDto hallDto) {
        return Hall.builder()
                .id(hallDto.id())
                .name(hallDto.name())
                .rows(hallDto.rows())
                .seatsPerRow(hallDto.seatsPerRow())
                .seats(hallDto.seats().stream()
                        .map(SeatDto::toEntity)
                        .toList())
                .build();
    }
}

