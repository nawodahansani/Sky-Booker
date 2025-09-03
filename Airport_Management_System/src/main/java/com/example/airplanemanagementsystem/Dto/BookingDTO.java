package com.example.airplanemanagementsystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingDTO {
    private Long id;
    private LocalDate bookingDate;
    private String passengerName;
    private String flightClass;
    private String paymentMethod;
    private String seatNumber;
    private String bookingStatus;
    private LocalDate createdAt;
}
