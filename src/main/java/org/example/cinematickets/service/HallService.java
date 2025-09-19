package org.example.cinematickets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.HallDto;
import org.example.cinematickets.model.Hall;
import org.example.cinematickets.model.Seat;
import org.example.cinematickets.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HallService {
    private final HallRepository hallRepository;

    @Transactional
    public HallDto createHall(String name, int rows, int seatsPerRow) {

        var hall = Hall.builder()
                .name(name)
                .rows(rows)
                .seatsPerRow(seatsPerRow)
                .build();


        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = Seat.builder()
                        .rowNumber(row)
                        .seatNumber(seatNum)
                        .hall(hall)
                        .build();
                seats.add(seat);
            }
        }
        hall.setSeats(seats);

        Hall savedHall = hallRepository.save(hall);
        return HallDto.fromEntity(savedHall);
    }

    public HallDto getHallById(Long id) {
        var hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hall with id " + id + " not found"));
        return HallDto.fromEntity(hall);
    }

}
