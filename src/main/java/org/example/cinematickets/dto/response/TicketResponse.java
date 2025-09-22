package org.example.cinematickets.dto.response;

import lombok.Builder;
import org.example.cinematickets.dto.TicketDto;

@Builder
public record TicketResponse (
        Long id,
        Long sessionId,
        String sessionMovieTitle,
        Long seatId,
        int seatRowNumber,
        int seatSeatNumber,
        String customerName,
        String customerEmail,
        Boolean paid,
        int price
) {

    public static TicketResponse fromEntity(TicketDto ticket) {
        return new TicketResponse(
                ticket.id(),
                ticket.session().id(),
                ticket.session().movie().title(),
                ticket.seat().id(),
                ticket.seat().rowNumber(),
                ticket.seat().seatNumber(),
                ticket.customerName(),
                ticket.customerEmail(),
                ticket.paid(),
                ticket.session().price()
        );
    }
}
