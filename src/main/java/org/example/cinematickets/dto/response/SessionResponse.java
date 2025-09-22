package org.example.cinematickets.dto.response;

import org.example.cinematickets.model.Session;

import java.time.LocalDateTime;

public record SessionResponse (
        Long id,
        Long movieId,
        String movieTitle,
        LocalDateTime startTime,
        int price
) {
    public static SessionResponse fromEntity(Session session) {
        return new SessionResponse(
                session.getId(),
                session.getMovie().getId(),
                session.getMovie().getTitle(),
                session.getStartTime(),
                session.getPrice()
        );
    }
}
