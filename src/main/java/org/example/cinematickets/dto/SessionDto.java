package org.example.cinematickets.dto;

import org.example.cinematickets.model.Session;

import java.time.LocalDateTime;

public record SessionDto(Long id,
                         MovieDto movie,
                         HallDto hall,
                         LocalDateTime startTime,
                         LocalDateTime endTime,
                         int price) {


    public static SessionDto fromEntity(Session session) {
        return new SessionDto(
                session.getId(),
                MovieDto.fromEntity(session.getMovie()),
                HallDto.fromEntity(session.getHall()),
                session.getStartTime(),
                session.getEndTime(),
                session.getPrice()
        );
    }

    public static Session toEntity(SessionDto sessionDto) {
        return Session.builder()
                .id(sessionDto.id())
                .movie(MovieDto.toEntity(sessionDto.movie()))
                .hall(HallDto.toEntity(sessionDto.hall()))
                .startTime(sessionDto.startTime())
                .endTime(sessionDto.endTime())
                .price(sessionDto.price())
                .build();
    }
}