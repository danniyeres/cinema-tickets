package org.example.cinematickets.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendTicket(String to, String subject, String text, byte[] qrCode ) throws MessagingException {
        try {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        helper.addAttachment("ticket.png", () -> new java.io.ByteArrayInputStream(qrCode));

        mailSender.send(message);
        log.debug("Sending email to {}", to);

        } catch (Exception e) {
            log.error("Failed to send email to {}", to, e);
            throw new MessagingException("Failed to send email", e);
        }
    }
}

