package org.example.cinematickets.dto.request;

public record BookingRequestDto (Long sessionId, Long seatId, String customerName, String customerEmail){

}
