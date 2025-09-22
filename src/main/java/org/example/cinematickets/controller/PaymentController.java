package org.example.cinematickets.controller;

import lombok.RequiredArgsConstructor;
import org.example.cinematickets.dto.response.TicketResponse;
import org.example.cinematickets.service.PaymentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/{ticketId}")
    public TicketResponse processPayment(@PathVariable Long ticketId) {
        var ticketDto = paymentService.processPayment(ticketId);
        return TicketResponse.fromEntity(ticketDto);
    }
}
