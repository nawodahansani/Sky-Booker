package com.example.airplanemanagementsystem.Service.impl;

import com.example.airplanemanagementsystem.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendBookingConfirmation(String toEmail, String passengerName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hasamahesh01@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Booking Confirmation");
        message.setText("Hello " + passengerName + ",\n\nYour booking has been successfully confirmed.Admin Will send You Air ticket later..\n\nThank you!");

        mailSender.send(message);
        System.out.println("Email sent to " + toEmail);
    }
}
