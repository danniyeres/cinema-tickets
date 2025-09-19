package org.example.cinematickets.dto;


import org.example.cinematickets.model.Ticket;

public record TicketDto(Long id,
                        String customerName,
                        String customerEmail,
                        Boolean paid,
                        SessionDto session,
                        SeatDto seat) {

    public static TicketDto fromEntity(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getCustomerName(),
                ticket.getCustomerEmail(),
                ticket.isPaid(),
                SessionDto.fromEntity(ticket.getSession()),
                SeatDto.fromEntity(ticket.getSeat())
        );
    }
}
