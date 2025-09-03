package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequestDTO {
    private LocalDate bookingDate;
    private String passengerName;
    private String flightClass;
    private String paymentMethod;
    private String seatNumber;
    private String packageName;
    private String email;
}
