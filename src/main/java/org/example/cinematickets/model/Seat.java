package org.example.cinematickets.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNumber;
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;
}
