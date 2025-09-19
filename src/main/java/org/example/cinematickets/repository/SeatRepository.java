package org.example.cinematickets.repository;

import org.example.cinematickets.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}