package org.example.cinematickets.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private String customerName;
    private String customerEmail;

    private boolean paid;



}
