package org.example.cinematickets.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.TicketDto;
import org.example.cinematickets.dto.request.BookingRequest;
import org.example.cinematickets.dto.response.TicketResponse;
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
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody BookingRequest bookingRequest) {
        var ticketDto = ticketBookingService.bookTicket(
                bookingRequest.sessionId(),
                bookingRequest.seatId(),
                bookingRequest.customerName(),
                bookingRequest.customerEmail()
        );

        var ticketResponse = TicketResponse.fromEntity(ticketDto);
        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/session/{sessionId}")
    public List<TicketResponse> getBySession(@PathVariable Long sessionId) {
         var ticketDto = ticketBookingService.getBookingsBySession(sessionId)
                .stream().map(TicketDto::fromEntity).toList();

         return ticketDto.stream().map(TicketResponse::fromEntity).toList();
    }

    @GetMapping("/{ticketId}")
    public TicketResponse getTicketById(@PathVariable Long ticketId) {
        var ticketDto = ticketBookingService.getTicketById(ticketId);
        return TicketResponse.fromEntity(ticketDto);
    }
}
