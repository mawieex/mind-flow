package com.appdev.MindFlow.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    // Inject JavaMailSender via constructor
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String to, String token) {
        String resetUrl = "http://localhost:8080/user/reset-password?token=" + token;
        String subject = "Password Reset Request on MindFlow";
        String body = "You requested a password reset. Click the link below to reset your password:\n\n" +
                      resetUrl + "\n\n" +
                      "If you didn't request this, please ignore this email.";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message); // ✅ Send the email properly
            System.out.println("Password reset email sent successfully!");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
