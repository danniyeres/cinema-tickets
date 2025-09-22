package org.example.cinematickets.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.cinematickets.dto.TicketDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final TicketBookingService bookingService;
    private final EmailService emailService;

    public TicketDto processPayment(Long ticketId) {
        var ticketDto = bookingService.getTicketById(ticketId);

        if (ticketDto == null) {
            throw new IllegalArgumentException("Ticket with id " + ticketId + " not found");
        }
        if (Boolean.TRUE.equals(ticketDto.paid())) {
            throw new IllegalStateException("Ticket with id " + ticketId + " is already paid");
        }

        var paidTicket = bookingService.markTicketAsPaid(ticketId);
        log.info("Payment for {} has been paid successfully", ticketId);

        String qrText = "TICKET-" + paidTicket.id();
        try {
            byte[] qrCode = QrCodeUtil.generateQrCode(qrText, 300, 300);

            String text = """
                    🎬 Movie: %s
                    🏛 Hall: %s
                    📅 Time: %s
                    💺 Seat: ряд %d, место %d
                    🔑 Code: %s
                    """.formatted(
                    paidTicket.session().movie().title(),
                    paidTicket.session().hall().name(),
                    paidTicket.session().startTime(),
                    paidTicket.seat().rowNumber(),
                    paidTicket.seat().seatNumber(),
                    qrText
            );

            emailService.sendTicket(
                    paidTicket.customerEmail(),
                    "Ticket for " + paidTicket.session().movie().title(),
                    text,
                    qrCode
            );
            log.info("Ticket for {} has been sent to {}", ticketId, paidTicket.customerEmail());

        } catch (Exception e) {
            log.error("Failed to send ticket for {} to {}", ticketId, paidTicket.customerEmail(), e);
            throw new RuntimeException("Не удалось отправить билет", e);
        }

        return paidTicket;
    }
}
