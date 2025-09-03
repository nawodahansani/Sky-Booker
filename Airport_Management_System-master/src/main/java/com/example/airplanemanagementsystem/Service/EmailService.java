package com.example.airplanemanagementsystem.Service;

public interface EmailService {
    void sendBookingConfirmation(String toEmail, String passengerName);
}
