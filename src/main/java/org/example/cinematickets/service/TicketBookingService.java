package org.example.cinematickets.service;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.SessionDto;
import org.example.cinematickets.dto.TicketDto;
import org.example.cinematickets.model.Ticket;
import org.example.cinematickets.repository.SeatRepository;
import org.example.cinematickets.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketBookingService {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final SessionService sessionService;


    public TicketDto bookTicket(Long sessionId, Long seatId, String customerName, String customerEmail) {
        var session = SessionDto.toEntity(sessionService.getSessionById(sessionId));
        var seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Seat with id " + seatId + " not found"));

        if (seat.getHall().getId() != session.getHall().getId()) {
            throw new IllegalArgumentException("Seat does not belong to the hall of the session");
        }

        boolean isSeatBooked = ticketRepository.existsBySessionAndSeat(session, seat);
        if (isSeatBooked) {
            throw new IllegalArgumentException("Seat is already booked for this session");
        }

        var ticket = Ticket.builder()
                .customerName(customerName)
                .customerEmail(customerEmail)
                .paid(false)
                .session(session)
                .seat(seat)
                .build();

        var savedTicket = ticketRepository.save(ticket);
        return TicketDto.fromEntity(savedTicket);
    }

    public TicketDto getTicketById(Long ticketId) {
        var ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket with id " + ticketId + " not found"));
        return TicketDto.fromEntity(ticket);
    }

    public List<Ticket> getBookingsBySession(Long sessionId) {
        return ticketRepository.findAllBySessionId(sessionId);
    }

    public TicketDto markTicketAsPaid(Long ticketId) {
        var ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket with id " + ticketId + " not found"));

        if (ticket.isPaid()) {
            throw new IllegalStateException("Ticket with id " + ticketId + " is already paid");
        }

        ticket.setPaid(true);
        var updatedTicket = ticketRepository.save(ticket);
        return TicketDto.fromEntity(updatedTicket);
    }
}
