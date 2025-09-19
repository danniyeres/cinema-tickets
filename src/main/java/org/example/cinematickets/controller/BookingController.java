package org.example.cinematickets.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.TicketDto;
import org.example.cinematickets.dto.request.BookingRequestDto;
import org.example.cinematickets.service.TicketBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final TicketBookingService ticketBookingService;

    @PostMapping
    public ResponseEntity<TicketDto> bookTicket(@RequestBody BookingRequestDto bookingRequestDto) {
        var ticketDto = ticketBookingService.bookTicket(
                bookingRequestDto.sessionId(),
                bookingRequestDto.seatId(),
                bookingRequestDto.customerName(),
                bookingRequestDto.customerEmail()
        );
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/session/{sessionId}")
    public List<TicketDto> getBySession(@PathVariable Long sessionId) {
        return ticketBookingService.getBookingsBySession(sessionId)
                .stream().map(TicketDto::fromEntity).toList();
    }
}
