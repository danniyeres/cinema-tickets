package org.example.cinematickets.repository;

import org.example.cinematickets.model.Seat;
import org.example.cinematickets.model.Session;
import org.example.cinematickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsBySessionAndSeat(Session session, Seat seat);
    List<Ticket> findAllBySessionId(Long sessionId);

}